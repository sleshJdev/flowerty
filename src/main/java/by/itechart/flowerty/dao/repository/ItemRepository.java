package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ItemRepository extends CrudRepository<Item, Long> {

}
