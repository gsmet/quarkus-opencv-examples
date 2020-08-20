package org.acme.rest.json.filters;

import org.acme.rest.json.filters.Filter;
import org.opencv.core.Mat;

import java.util.ArrayList;

public class FilterConfig {

    private static ArrayList<Filter> sequence = new ArrayList<>();


    public static void add(Filter filter){
        sequence.add(filter);
    }

    public static void add(ArrayList<Filter> filters){
        sequence = filters;
    }

    public static Mat runConfigs(Mat imageMatrix){
        Mat dst = imageMatrix;
        for(Filter f: sequence){
            dst = f.process(dst);
        }
        return dst;
    }


}
