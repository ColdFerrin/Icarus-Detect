package org.erau.icarus.detect.MariaDB.Model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class Camera {

    @Id
    @Column(name = "CamID")
    private int camID;

    @Column(name = "Camlat")
    private float camLat;

    @Column(name = "Camlong")
    private float camLong;

    @Column(name = "CameraModel_ModelID")
    private String camModelId;

    @Column(name = "CameraLens_LensID")
    private String CameraLens_LensID;
}
