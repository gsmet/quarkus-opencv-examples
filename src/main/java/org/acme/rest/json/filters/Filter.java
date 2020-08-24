package org.acme.rest.json.filters;

import org.opencv.core.Mat;

public interface Filter {

    public Mat process(Mat src);

}
