package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Company;
import by.itechart.flowerty.persistence.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Long>, ContactRepositoryCustom {
    public Contact findOne(Long id);
    public Page<Contact> findByCompany(Company company, Pageable pageable);
    public Page<Contact> findBySurnameStartingWithAndCompany(String surname, Company company, Pageable pageable);
    public List<Contact> findBySurnameStartingWithAndCompany(String surname, Company company);
    public Page<Contact> findByIdIsIn(List<Long> list, Pageable pageable);
    public List<Contact> findByIdIn(List<Long> list);
}
