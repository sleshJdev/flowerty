package by.itechart.flowerty.persistence.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import by.itechart.flowerty.persistence.repository.model.Phone;

public interface PhoneRepository extends PagingAndSortingRepository<Phone, Long>, QueryDslPredicateExecutor<Phone>, PhoneRepositoryCustom{
    public Phone findOne(Long id);

    @SuppressWarnings("unchecked")
    public Phone save(Phone phone);

    public void delete(Long id);
}
