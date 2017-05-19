package br.com.dijalmasilva.pdm.repository;

import br.com.dijalmasilva.pdm.models.Record;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 18/05/17.
 */
@Repository
public interface RecordRepository extends CrudRepository<Record, Long> {

    List<Record> findByPathRecordContainingOrderByDateTimeDesc(String nameCam);
}
