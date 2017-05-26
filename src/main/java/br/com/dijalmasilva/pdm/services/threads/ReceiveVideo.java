package br.com.dijalmasilva.pdm.services.threads;

import br.com.dijalmasilva.pdm.converts.RecordFormConvert;
import br.com.dijalmasilva.pdm.enums.Constant;
import br.com.dijalmasilva.pdm.forms.RecordForm;
import br.com.dijalmasilva.pdm.models.Record;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 21/05/17.
 */
public class ReceiveVideo implements Runnable {

    private boolean activate;
    private String pathTemp = "/resources/tempFiles";
    private String extension = ".mp4";

    public ReceiveVideo(boolean activate) {
        this.activate = activate;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverVideos = new ServerSocket(1090);

            while (activate) {
                Socket accept = serverVideos.accept();
                ObjectInputStream in = new ObjectInputStream(accept.getInputStream());
                RecordForm recordForm = null;
                //
                while (recordForm == null) {
                    recordForm = (RecordForm) in.readObject();
                }
                //
                createDirectoryTemp();
                String path = new File(".").getAbsolutePath() + pathTemp + File.separator + generateNameTemp() + extension;
                FileOutputStream file = new FileOutputStream(path);
                byte[] buffer = new byte[1024];
                while (true) {
                    int len = in.read(buffer);
                    if (len < 0) {
                        file.close();
                        break;
                    }
                    file.write(buffer, 0, len);
                }
                File record = new File(path);
                recordForm.setRecord(record);
                RecordFormConvert recordFormConvert = new RecordFormConvert();
                Record convert = recordFormConvert.convert(recordForm);
                //
                System.out.println("Arquivo recebido com tamanho: " + record.length());
                deleteFilesTemp(path);
                sendToWebServiceThatSaveRecord(convert);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void sendToWebServiceThatSaveRecord(Record record) {
        final String uri = Constant.HOST_WITH_PROTOCOL.getValue() + "/record";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(uri, record, Record.class);
    }

    private void createDirectoryTemp() {
        File f = new File(new File("."), pathTemp);
        System.out.println("PATH COMPLETO: " + f.getAbsolutePath());
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    private String generateNameTemp() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("ddMMyyyHHmmss"));
    }

    private void deleteFilesTemp(String path) {
        new File(path).delete();
    }
}
