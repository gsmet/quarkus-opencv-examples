package org.acme.rest.json.filters;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class AdaptiveThreshold implements Filter{

    @Override
    public Mat process(Mat src) {
        Mat dst = src;
        Imgproc.adaptiveThreshold(src, dst, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.ADAPTIVE_THRESH_MEAN_C, 9,2);
        return dst;
    }
}
