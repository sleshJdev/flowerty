package by.itechart.flowerty.dao.repository;

import org.springframework.data.repository.CrudRepository;

import by.itechart.flowerty.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
