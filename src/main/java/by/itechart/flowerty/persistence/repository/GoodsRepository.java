package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Company;
import by.itechart.flowerty.persistence.model.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GoodsRepository extends PagingAndSortingRepository<Goods, Long> {
    public Goods findOne(Long id);

    public Page<Goods> findByCompany(Company company, Pageable pageable);
}
