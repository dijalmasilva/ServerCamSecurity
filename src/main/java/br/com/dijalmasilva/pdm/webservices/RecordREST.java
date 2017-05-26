package br.com.dijalmasilva.pdm.webservices;

import br.com.dijalmasilva.pdm.models.Record;
import br.com.dijalmasilva.pdm.services.RecordService;
import br.com.dijalmasilva.pdm.forms.RecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 18/05/17.
 */
@RestController
@RequestMapping("/record")
public class RecordREST {

    private final RecordService service;

    @Autowired
    public RecordREST(RecordService service) {
        this.service = service;
    }

    @GetMapping("/camera/{nameCam}")
    public List<Record> getAllForCamera(@PathVariable String nameCam) {
        return service.findByContainsNameCam(nameCam);
    }

    @PostMapping
    public Record saveRecord(@RequestBody Record record) {
        return service.save(record);
    }

    @GetMapping("/{id}")
    public Record getRecord(@PathVariable Long id) {
        return service.findOne(id);
    }
}
