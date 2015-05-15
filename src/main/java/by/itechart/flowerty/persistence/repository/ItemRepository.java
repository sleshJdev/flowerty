package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Item;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
    public Item findOne(Long id);
}
