package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.repository.model.Item;
import by.itechart.flowerty.persistence.repository.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
    public Item save(Item item);
    public void delete (Item item);
    public Page<Item> findByOrder(Order order, Pageable pageable);
    public Item  findOne(Long id);
}
