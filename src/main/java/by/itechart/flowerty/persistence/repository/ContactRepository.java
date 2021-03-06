package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Company;
import by.itechart.flowerty.persistence.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Long>, ContactRepositoryCustom {
    public List<Contact> findByBirthday(Date date);

    public Contact findOne(Long id);
    
    public Page<Contact> findByCompany(Company company, Pageable pageable);

    public Page<Contact> findBySurnameStartingWithAndCompany(String surname, Company company, Pageable pageable);

    public List<Contact> findBySurnameStartingWithAndCompany(String surname, Company company);

    public Page<Contact> findByIdIsInAndCompany(List<Long> list, Company company, Pageable pageable);

    public List<Contact> findByIdIsInAndCompany(List<Long> list, Company company);
}
