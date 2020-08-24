package org.acme.filters;

import org.opencv.core.Core;
import org.opencv.core.Mat;

public class BitwiseAnd{

    public Mat process(Mat blur, Mat edged) {
        Mat dst = blur;
        Core.bitwise_and(blur, edged, dst);
        return dst;
    }
}
