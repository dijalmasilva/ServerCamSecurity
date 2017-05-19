package br.com.dijalmasilva.pdm.converts;

import br.com.dijalmasilva.pdm.forms.RecordVO;
import br.com.dijalmasilva.pdm.models.Record;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.format.DateTimeFormatter;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 18/05/17.
 */
public class RecordVOConverter {

    private final static String PATH_VIDEOS = "/resources/videos/";
    private final static String PATH_IMAGES = "/resources/images/";
    private final static String TYPE_VIDEO = ".mp4";
    private final static String TYPE_IMAGE = ".png";

    public Record convert(RecordVO recordVO) {
        Record record = new Record();
        String nameWebCam = recordVO.getNameWebCam();
        String dateTime = recordVO.getDateTime().format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss"));
        record.setPathRecord(getPathRecord(nameWebCam, dateTime));
        record.setImgPreview(getPathImgPreview(nameWebCam, dateTime));
        createVideoFileAndSave(recordVO, record);
        createImagePreviewAndSave(record);
        return record;
    }

    private String getPathRecord(String nameWebCam, String dateTime) {
        return PATH_VIDEOS + File.separator + nameWebCam + File.pathSeparator + dateTime + TYPE_VIDEO;
    }

    private String getPathImgPreview(String nameWebCam, String dateTime) {
        return PATH_IMAGES + File.separator + nameWebCam + File.separator + dateTime + TYPE_IMAGE;
    }

    private void createVideoFileAndSave(RecordVO recordVO, Record record) {
        try {
            byte[] bytes = recordVO.getRecord().getBytes();
            File fileRecord = new File(record.getPathRecord());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileRecord));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createImagePreviewAndSave(Record record) {
        File file = new File(record.getImgPreview());
        try {
            Java2DFrameConverter converter = new Java2DFrameConverter();
            FFmpegFrameGrabber g = new FFmpegFrameGrabber(record.getPathRecord());
            g.start();
            Frame frame = g.grab();
            BufferedImage grabbedImage = converter.convert(frame);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(grabbedImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDirectoriesWebCam(String nameWebCam) {
        String pathVideo = PATH_VIDEOS + File.separator + nameWebCam;
        String pathImage = PATH_IMAGES + File.separator + nameWebCam;
        createDirectory(pathVideo);
        createDirectory(pathImage);
    }

    private void createDirectory(String directory) {
        File f = new File(directory);
        if (!f.exists()) {
            f.mkdirs();
        }
    }
}
