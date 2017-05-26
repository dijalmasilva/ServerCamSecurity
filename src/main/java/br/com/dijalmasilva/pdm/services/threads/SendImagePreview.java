package br.com.dijalmasilva.pdm.services.threads;

import br.com.dijalmasilva.pdm.enums.Constant;
import br.com.dijalmasilva.pdm.models.Record;
import br.com.dijalmasilva.pdm.services.RecordConsume;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 22/05/17.
 */
public class SendImagePreview implements Runnable {

    private boolean activate;

    public SendImagePreview(boolean activate) {
        this.activate = activate;
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(1091);

            while (activate) {
                Socket accept = server.accept();
                System.out.println("Requisição recebida...");
                InputStream in = accept.getInputStream();
                byte[] idByte = new byte[in.available()];
                int read = in.read(idByte);
                while (read <= 0) {
                    read = in.read(idByte);
                }
                Long idRecord = Long.parseLong(new String(idByte).trim());
                System.out.println("Gravação de id: " + idRecord);
                Record recordFound = RecordConsume.findRecordByWebService(idRecord);
                OutputStream outputStream = accept.getOutputStream();
                FileInputStream inputStreamImage = new FileInputStream(recordFound.getImgPreview());
                byte[] buffer = new byte[100];
                while (true) {
                    int len = inputStreamImage.read(buffer);
                    if (len < 0) {
                        break;
                    }
                    outputStream.write(buffer, 0, len);
                    outputStream.flush();
                }
                inputStreamImage.close();
                in.close();
                outputStream.close();
                //
                System.out.println("Imagem enviada!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private Record findRecordByWebService(Long idRecord) {
//        String uri = Constant.HOST_WITH_PROTOCOL.getValue() + "/record/{id}";
//        Map<String, String> params = new HashMap<>();
//        params.put("id", "" + idRecord);
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject(uri, Record.class, params);
//    }
}
