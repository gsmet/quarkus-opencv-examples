package org.acme.filters;

import org.opencv.core.Mat;

public interface Filter {

    public Mat process(Mat src);

}
