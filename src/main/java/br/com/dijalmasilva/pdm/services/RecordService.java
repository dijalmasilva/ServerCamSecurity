package br.com.dijalmasilva.pdm.services;

import br.com.dijalmasilva.pdm.models.Record;
import br.com.dijalmasilva.pdm.repository.RecordRepository;
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

    public Record save(Record record) {
        return dao.save(record);
    }

    public Record findOne(Long id) {
        return dao.findOne(id);
    }

    public void delete(Long id) {
        dao.delete(id);
    }

//    public Record save(RecordForm recordForm) {
//        RecordFormConvert recordFormConvert = new RecordFormConvert();
//        return dao.save(recordFormConvert.convert(recordForm));
//    }

    public List<Record> findByContainsNameCam(String nameCam) {
        return dao.findByPathRecordContainingOrderByDateTimeDesc(nameCam);
    }
}
