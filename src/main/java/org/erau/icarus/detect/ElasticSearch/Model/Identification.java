package org.erau.icarus.detect.ElasticSearch.Model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class Identification {
    @Field(type = FieldType.Boolean)
    private boolean correct;

    @Field(type = FieldType.Boolean)
    private boolean isTraining;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Drone drone;
}
