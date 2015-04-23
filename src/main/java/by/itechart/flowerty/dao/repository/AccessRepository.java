package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.Right;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Rostislav on 23-Apr-15
 */
public interface AccessRepository extends CrudRepository<Right, Long> {
    public List<Right> findAll();
}
