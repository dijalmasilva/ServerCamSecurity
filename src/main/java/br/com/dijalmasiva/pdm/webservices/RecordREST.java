package br.com.dijalmasiva.pdm.webservices;

import br.com.dijalmasiva.pdm.forms.RecordVO;
import br.com.dijalmasiva.pdm.models.Record;
import br.com.dijalmasiva.pdm.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Record saveRecord(@RequestBody RecordVO recordVO) {
        return service.save(recordVO);
    }

    @GetMapping("/{id}")
    public Record getRecord(@PathVariable Long id) {
        return service.findOne(id);
    }
}
