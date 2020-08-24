package org.acme.rest.json.filters;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class RGB2Grey implements Filter{

    @Override
    public Mat process(Mat src) {

        Mat dst = src;
        Imgproc.cvtColor(src, dst, Imgproc.COLOR_RGB2GRAY);

        return dst;
    }
}
