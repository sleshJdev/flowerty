package by.itechart.flowerty.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import by.itechart.flowerty.persistence.model.Phone;

public interface PhoneRepository extends PagingAndSortingRepository<Phone, Long>, PhoneRepositoryCustom{
    public Phone findOne(Long id);
}
