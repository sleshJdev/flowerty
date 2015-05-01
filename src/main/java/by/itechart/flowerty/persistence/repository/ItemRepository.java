package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Item;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
    public Item save(Item item);
    public void delete (Item item);
  //  public Page<Item> findByOrder(Order order, Pageable pageable);
    public Item  findOne(Long id);
}
