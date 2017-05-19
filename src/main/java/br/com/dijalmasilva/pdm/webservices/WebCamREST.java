package br.com.dijalmasilva.pdm.webservices;

import br.com.dijalmasilva.pdm.models.WebCam;
import br.com.dijalmasilva.pdm.services.WebCamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 18/05/17.
 */
@RestController
@RequestMapping("/webcam")
public class WebCamREST {

    private final WebCamService service;

    @Autowired
    public WebCamREST(WebCamService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public WebCam getWebCam(@PathVariable Long id) {
        return service.findOne(id);
    }

    @PostMapping
    public WebCam saveWebCam(@RequestBody WebCam webCam) {
        return service.save(webCam);
    }

    @PutMapping("/{id}")
    public WebCam updateWebCam(@PathVariable Long id, @RequestBody WebCam webCam) {
        return service.update(id, webCam);
    }
}
