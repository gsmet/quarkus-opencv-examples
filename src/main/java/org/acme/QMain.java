package org.acme;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import nu.pattern.OpenCV;
import org.acme.filters.*;
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
        Mat img_color = imageMatrix;
        Mat img_gray = imageMatrix;
        Mat img_blur = imageMatrix;
        Mat img_edge = imageMatrix;

        img_color = new PyrDown().process(imageMatrix);
        img_color = new BilateralFilter().process(img_color);
        img_color = new PyrUp().process(img_color);

        img_gray = new RGB2Grey().process(imageMatrix);

        img_blur = new MedianBlur().process(img_gray);
        img_edge = new AdaptiveThreshold().process(img_blur);
        img_edge = new Grey2RGB().process(img_edge);

        img_edge = new BitwiseAnd().process(img_color, img_edge);

        return new DetectFace().process(img_edge);

    }

    public static void createConfig(){
        FilterConfig.add(new PyrDown());
        FilterConfig.add(new BilateralFilter());
        FilterConfig.add(new PyrUp());
        FilterConfig.add(new RGB2Grey());
        FilterConfig.add(new MedianBlur());
        FilterConfig.add(new AdaptiveThreshold());
        FilterConfig.add(new Grey2RGB());

    }


    public static void saveImage(Mat imageMatrix, String targetPath) {
        Imgcodecs imgcodecs = new Imgcodecs();
        imgcodecs.imwrite(targetPath, imageMatrix);
    }

}
