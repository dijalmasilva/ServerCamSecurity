package br.com.dijalmasilva.pdm.services;

import br.com.dijalmasilva.pdm.models.WebCam;
import br.com.dijalmasilva.pdm.repository.WebCamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 18/05/17.
 */
@Service
public class WebCamService {

    private final WebCamRepository dao;

    @Autowired
    public WebCamService(WebCamRepository dao) {
        this.dao = dao;
    }

    public WebCam save(WebCam webCam) {
        webCam.setNameCam(generateNameCam());
        webCam.setActivate(true);
        return dao.save(webCam);
    }

    public WebCam update(Long id, WebCam webCam) {
        WebCam one = dao.findOne(id);
        one = webCam;
        return dao.save(one);
    }

    public WebCam findOne(Long id) {
        return dao.findOne(id);
    }

    private String generateNameCam() {
        return "CAM_" +
                generateRandomStringOfSizeFixed(6, false) +
                "-" +
                generateRandomStringOfSizeFixed(2, true);
    }

    private String generateRandomStringOfSizeFixed(int length, boolean justNumbers) {
        String numbersLetter = "ABCDEFGHIJKLMNOPQRSTUVXWYZ0123456789";
        String numbers = "0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        int j;
        for (int i = 0; i < length; i++) {
            if (justNumbers) {
                j = random.nextInt(numbers.length());
                result.append(numbers.charAt(j));
            } else {
                j = random.nextInt(numbersLetter.length());
                result.append(numbersLetter.charAt(j));
            }
        }
        return result.toString();
    }

}
