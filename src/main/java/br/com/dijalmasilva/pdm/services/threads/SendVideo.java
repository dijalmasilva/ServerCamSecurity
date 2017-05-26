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
public class SendVideo implements Runnable {

    private boolean activate;

    public SendVideo(boolean activate) {
        this.activate = activate;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(1092);
            while (activate) {
                Socket accept = serverSocket.accept();
                ObjectInputStream in = new ObjectInputStream(accept.getInputStream());
                Long idRecord = null;
                while (idRecord == null) {
                    idRecord = in.readLong();
                }
                Record record = RecordConsume.findRecordByWebService(idRecord);
                ObjectOutputStream out = new ObjectOutputStream(accept.getOutputStream());
                InputStream inputStream = new FileInputStream(record.getPathRecord());
                byte[] buffer = new byte[1024];
                while (true) {
                    int len = inputStream.read(buffer);
                    if (len < 0) {
                        break;
                    }
                    out.write(buffer, 0, len);
                }
                //
                inputStream.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
