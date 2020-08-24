package org.acme.filters;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class GaussianBlur implements Filter {

    @Override
    public Mat process(Mat src) {
        int MAX_KERNEL_LENGTH = 31;
        Mat dst = new Mat();
        for (int i = 1; i < MAX_KERNEL_LENGTH; i = i + 2) {
            Imgproc.GaussianBlur(src, dst, new Size(i, i), 0, 0);
        }
        return dst;
    }
}
