package br.com.dijalmasiva.pdm.services;

import br.com.dijalmasiva.pdm.converts.RecordVOConverter;
import br.com.dijalmasiva.pdm.forms.RecordVO;
import br.com.dijalmasiva.pdm.models.Record;
import br.com.dijalmasiva.pdm.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 18/05/17.
 */
@Service
public class RecordService {

    private final RecordRepository dao;

    @Autowired
    public RecordService(RecordRepository dao) {
        this.dao = dao;
    }

    private Record save(Record record) {
        return dao.save(record);
    }

    public Record findOne(Long id) {
        return dao.findOne(id);
    }

    public void delete(Long id) {
        dao.delete(id);
    }

    public Record save(RecordVO recordVO) {
        RecordVOConverter converterVO = new RecordVOConverter();
        return converterVO.convert(recordVO);
    }

    public List<Record> findByContainsNameCam(String nameCam) {
        return dao.findByPathRecordContainingOrderByDateTimeDesc(nameCam);
    }
}
