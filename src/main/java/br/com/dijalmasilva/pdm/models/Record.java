package br.com.dijalmasilva.pdm.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 18/05/17.
 */
@Entity
public class Record implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateTime;
    private String pathRecord;
    private String imgPreview;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getPathRecord() {
        return pathRecord;
    }

    public void setPathRecord(String pathRecord) {
        this.pathRecord = pathRecord;
    }

    public String getImgPreview() {
        return imgPreview;
    }

    public void setImgPreview(String imgPreview) {
        this.imgPreview = imgPreview;
    }
}
