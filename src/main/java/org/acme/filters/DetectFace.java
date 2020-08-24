package org.acme.filters;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class DetectFace implements Filter{
    @Override
    public Mat process(Mat src) {
        MatOfRect faces = new MatOfRect();
        CascadeClassifier cc = new CascadeClassifier("haarcascade_frontalface_default.xml");
        cc.detectMultiScale(src,faces);

        System.out.println(String.format("Detected %s faces",
                faces.toArray().length));

        // Drawing boxes
        for (Rect rect : faces.toArray()) {
            Imgproc.rectangle(
                    src,
                    new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 0, 255),
                    3
            );
        }

        return src;
    }
}
