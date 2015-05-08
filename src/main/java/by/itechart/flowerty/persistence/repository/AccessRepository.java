package by.itechart.flowerty.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import by.itechart.flowerty.persistence.model.Right;

/**
 * Created by Rostislav on 23-Apr-15
 */
public interface AccessRepository extends CrudRepository<Right, Long> {
    @Transactional
    public List<Right> findAll();
}
