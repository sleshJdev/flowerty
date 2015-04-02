package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.Company;
import by.itechart.flowerty.model.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface GoodsRepository extends PagingAndSortingRepository<Goods, Long> {
    public Goods findOne(Long id);
    public Page<Goods> findByCompany(Company company, Pageable pageable);
    public Goods save (Goods goods);
    public void delete(Long id);
}
