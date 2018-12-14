package org.erau.icarus.detect.ElasticSearch.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.elasticsearch.common.util.FloatArray;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName = "icarus-drone-id")
public class _doc {
    @Id
    private String id;

    @JsonProperty("@timestamp")
    @Field(type = FieldType.Date)
    private Date timestamp;

    private String image;

    private FloatArray score;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Camera camera;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Identification identification;
}
