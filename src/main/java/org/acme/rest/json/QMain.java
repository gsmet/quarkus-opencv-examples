package org.acme.rest.json;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import nu.pattern.OpenCV;
import org.acme.rest.json.filters.*;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;

@QuarkusMain
public class QMain implements QuarkusApplication {


    final String testImage = "/home/sysh/Pictures/TestImg-300x300.jpg";
    final String targetImage = "/home/sysh/Pictures/TestImg-301x301.jpg";


    @Override
    public int run(String... args) throws Exception {

        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        OpenCV.loadShared();

        System.out.println("Loading OpenCV "+Core.NATIVE_LIBRARY_NAME);
        System.out.println("Loading OpenCV "+Core.VERSION);
        Mat m = loadImage(testImage);
        createConfig();
        m = transformImage(m);
        saveImage(m, targetImage);
        return 0;
    }

    public static Mat loadImage(String imagePath) {
        Imgcodecs imageCodecs = new Imgcodecs();
        return imageCodecs.imread(imagePath);
    }


    public static Mat transformImage(Mat imageMatrix){
        return FilterConfig.runConfigs(imageMatrix);
    }

    public static void createConfig(){
        FilterConfig.add(new PyrDown());
        FilterConfig.add(new BilateralFilter());
        FilterConfig.add(new PyrUp());
        FilterConfig.add(new MedianBlur());
    }


    public static void saveImage(Mat imageMatrix, String targetPath) {
        Imgcodecs imgcodecs = new Imgcodecs();
        imgcodecs.imwrite(targetPath, imageMatrix);
    }

}
