package br.com.dijalmasilva.pdm.repository;

import br.com.dijalmasilva.pdm.models.WebCam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 18/05/17.
 */
@Repository
public interface WebCamRepository extends CrudRepository<WebCam, Long> {
}
