package br.com.dijalmasilva.pdm.converts;

import br.com.dijalmasilva.pdm.forms.RecordForm;
import br.com.dijalmasilva.pdm.models.Record;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 18/05/17.
 */
public class RecordFormConvert {

    private final static String PATH_VIDEOS = "/resources/videos/";
    private final static String PATH_IMAGES = "/resources/images/";
    private final static String TYPE_VIDEO = ".mp4";
    private final static String TYPE_IMAGE = ".png";

    public Record convert(RecordForm recordForm) {
        Record record = new Record();
        String nameWebCam = recordForm.getNameWebCam();
        LocalDate date = recordForm.getDate().toLocalDate();
        LocalDateTime hour = recordForm.getHour().toLocalDateTime();
        LocalDateTime dateFinal = LocalDateTime.of(date, LocalTime.from(hour));
        String dateTime = dateFinal.format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss"));
        record.setDateTime(dateFinal);
        record.setPathRecord(getPathRecord(nameWebCam, dateTime));
        record.setImgPreview(getPathImgPreview(nameWebCam, dateTime));
        createDirectoriesWebCam(nameWebCam);
        createVideoFileAndSave(recordForm.getRecord(), record);
        createImagePreviewAndSave(record);
        return record;
    }

    private String getPathRecord(String nameWebCam, String dateTime) {
        return new File(".").getAbsolutePath() + PATH_VIDEOS +
                nameWebCam + File.separator + dateTime + TYPE_VIDEO;
    }

    private String getPathImgPreview(String nameWebCam, String dateTime) {
        return new File(".").getAbsolutePath() + PATH_IMAGES +
                nameWebCam + File.separator + dateTime + TYPE_IMAGE;
    }

    private void createVideoFileAndSave(File recordFile, Record record) {
        try {
            File fileRecord = new File(record.getPathRecord());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileRecord));
            byte[] bytes = fileToArrayBytes(recordFile);
            if (bytes != null) {
                stream.write(bytes);
            } else {
                return;
            }
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
            ImageIO.write(grabbedImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDirectoriesWebCam(String nameWebCam) {
        String pathVideo = new File(".").getAbsolutePath() + PATH_VIDEOS + nameWebCam;
        String pathImage = new File(".").getAbsolutePath() + PATH_IMAGES + nameWebCam;
        createDirectory(pathVideo);
        createDirectory(pathImage);
    }

    private void createDirectory(String directory) {
        File f = new File(directory);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    private byte[] fileToArrayBytes(File file) {
        byte[] bytes = new byte[(int) file.length()];
        try {
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytes);
            fis.close();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
