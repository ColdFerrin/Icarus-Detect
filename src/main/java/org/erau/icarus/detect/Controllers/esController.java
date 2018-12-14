package org.erau.icarus.detect.Controllers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.xcontent.XContentType;
import org.erau.icarus.detect.Config.TestEntries;
import org.erau.icarus.detect.ElasticSearch.Model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/es")
public class esController {

    private static final ObjectMapper DEFAULT_JSON_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(JsonParser.Feature.ALLOW_COMMENTS)
            .findAndRegisterModules();

    private RestHighLevelClient client;

    public esController() {
    }

    @PostConstruct
    private void init(){
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost",9200, "http")
                )
        );
    }

    @PreDestroy
    private void destroy() throws IOException {
        client.close();
    }

    @RequestMapping(value = "/randInsert")
    public ResponseEntity<?> randInsert() throws IOException {
        Random random = new Random();

        Drone drone = new Drone();
        drone.setMake(TestEntries.getDroneMake(random));
        drone.setModel(TestEntries.getDronemodel(random));
        drone.setModelNo(TestEntries.getDronemodelno(random));

        CameraModel cameraModel = new CameraModel();
        cameraModel.setLens("This Lens");
        cameraModel.setMake(TestEntries.getCammake(random));
        cameraModel.setModel(TestEntries.getCammodel(random));

        Camera camera = new Camera();
        camera.setCameraModel(cameraModel);
        camera.setCamID(1);
        camera.setLocation(GeoPoint.parseFromLatLon(TestEntries.getLatLong(random)));

        Identification identification = new Identification();
        identification.setCorrect(random.nextBoolean());
        identification.setDrone(drone);
        identification.setTraining(random.nextBoolean());

        _doc doc = new _doc();
        doc.setCamera(camera);
        doc.setIdentification(identification);
        doc.setImage(TestEntries.getRandImage(random));
        doc.setScore(TestEntries.getScoreVectorArray(random));
        doc.setTimestamp(Date.from(Instant.now()));

        String jsonString = DEFAULT_JSON_MAPPER.writeValueAsString(doc);

        IndexRequest indexRequest = new IndexRequest("icarus-drone-id", "_doc", "1");

        indexRequest.source(jsonString, XContentType.JSON);

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

        return new ResponseEntity<>(indexResponse.toString(), HttpStatus.OK);
    }
}
