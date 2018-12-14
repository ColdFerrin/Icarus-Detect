package org.erau.icarus.detect.Config;

import java.util.Arrays;
import java.util.Random;

public class TestEntries {

    private static final String[] cammake = {"Nikon","Cannon","Samsung"};
    private static final String[] cammodel = {"D7300","T6i","S7","T16aExtreme"};
    private static final String[] cammodelno = {"1-54784930O","192-834578Y","81239-4392H","12346789-0A"};
    private static final String[] droneMake = {"DJI","Parrot","DOD"};
    private static final String[] dronemodel = {"1","2","air","pro"};
    private static final String[] dronemodelno = {"A-759123674","G7-43172864","L374612-39","F761548-27562"};
    private static final String image1 = "ABCD==";
    private static final String image2 = "EFGH==";
    private static final String image3 = "IJKL==";

    public static String getCammake(Random random) {
        return cammake[random.nextInt(cammake.length)];
    }

    public static String getCammodel(Random random) {
        return cammodel[random.nextInt(cammodel.length)];
    }

    public static String getCammodelno(Random random) {
        return cammodelno[random.nextInt(cammodelno.length)];
    }

    public static String getDroneMake(Random random) {
        return droneMake[random.nextInt(droneMake.length)];
    }

    public static String getDronemodel(Random random) {
        return dronemodel[random.nextInt(dronemodel.length)];
    }

    public static String getDronemodelno(Random random) {
        return dronemodelno[random.nextInt(dronemodelno.length)];
    }

    public static String getRandImage(Random random) {
        int imNum = random.nextInt(3);
        if (imNum == 1)
            return image1;
        else if (imNum == 2)
            return image2;
        else
            return image3;
    }

    public static String getScoreVector(Random random) {
        float[] toReturn = new float[128];
        for (int i = 0; i < toReturn.length; i++) {
            toReturn[i] = random.nextFloat();
        }
        return Arrays.toString(toReturn);
    }

    public static float[] getScoreVectorArray(Random random) {
        float[] toReturn = new float[128];
        for (int i = 0; i < toReturn.length; i++) {
            toReturn[i] = random.nextFloat();
        }
        return toReturn;
    }

    public static String getLatLong(Random random) {
        float lon = random.nextFloat() * (180 - (-180)) + (-180);
        float lat = random.nextFloat() * (90 - (-90)) + (-90);

        return lat + "," + lon;
    }
}
