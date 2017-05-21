package br.com.dijalmasilva.pdm.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 18/05/17.
 */
@Entity
public class WebCam implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String nameCam;
    private boolean activate;
    private boolean recording;

    public WebCam() {
    }

    public WebCam(String nameCam) {
        this.nameCam = nameCam;
        this.activate = true;
        this.recording = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCam() {
        return nameCam;
    }

    public void setNameCam(String nameCam) {
        this.nameCam = nameCam;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public boolean isRecording() {
        return recording;
    }

    public void setRecording(boolean recording) {
        this.recording = recording;
    }
}
