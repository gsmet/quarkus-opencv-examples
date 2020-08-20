package org.acme.rest.json.filters;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class PyrDown implements Filter{
    @Override
    public String filterName() {
        return "PyrDown";
    }

    @Override
    public Mat process(Mat src) {
        Mat dst = src;
        Imgproc.pyrDown(src, dst);
        return dst;
    }
}
