package org.acme.rest.json.filters;

import org.opencv.core.Mat;

public interface Filter {

    public String filterName();

    public Mat process(Mat src);

}
