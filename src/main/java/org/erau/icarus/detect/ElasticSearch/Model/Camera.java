package org.erau.icarus.detect.ElasticSearch.Model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Data
public class Camera {
    @Field(type = FieldType.Integer)
    private int camID;

    private GeoPoint location;

    @Field(type = FieldType.Nested, includeInParent = true)
    private CameraModel cameraModel;

}
