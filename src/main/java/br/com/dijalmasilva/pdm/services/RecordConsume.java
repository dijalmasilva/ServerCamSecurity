package br.com.dijalmasilva.pdm.services;

import br.com.dijalmasilva.pdm.enums.Constant;
import br.com.dijalmasilva.pdm.models.Record;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 22/05/17.
 */
public class RecordConsume {

    public static Record findRecordByWebService(Long idRecord) {
        String uri = Constant.HOST_WITH_PROTOCOL.getValue() + "/record/{id}";
        Map<String, String> params = new HashMap<>();
        params.put("id", "" + idRecord);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, Record.class, params);
    }
}
