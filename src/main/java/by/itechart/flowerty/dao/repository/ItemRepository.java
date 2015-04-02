package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.Item;
import by.itechart.flowerty.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
    public Item save(Item item);
    public void delete (Item item);
    public Page<Item> findByOrder(Order order, Pageable pageable);
    public Item  findOne(Long id);
}
