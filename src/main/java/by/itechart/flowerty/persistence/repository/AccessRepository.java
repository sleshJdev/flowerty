package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.repository.model.Right;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Rostislav on 23-Apr-15
 */
public interface AccessRepository extends CrudRepository<Right, Long> {
    @Transactional
    public List<Right> findAll();
}
