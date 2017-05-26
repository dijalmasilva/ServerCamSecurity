package br.com.dijalmasilva.pdm;

import br.com.dijalmasilva.pdm.services.threads.ReceiveVideo;
import br.com.dijalmasilva.pdm.services.threads.SendImagePreview;
import br.com.dijalmasilva.pdm.services.threads.SendVideo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerCamSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerCamSecurityApplication.class, args);
        executeThreadReceiveVideos();
        executeThreadSendImagePreview();
        executeThreadSendVideo();
    }

    private static void executeThreadReceiveVideos() {
        new Thread(new ReceiveVideo(true)).start();
    }

    private static void executeThreadSendImagePreview() {
        new Thread(new SendImagePreview(true)).start();
    }

    private static void executeThreadSendVideo() {
        new Thread(new SendVideo(true)).start();
    }

}
