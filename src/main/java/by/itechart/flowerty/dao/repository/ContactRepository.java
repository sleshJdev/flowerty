package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.Company;
import by.itechart.flowerty.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {
    public Contact findOne(Long id);
    public List<Contact> findByCompany(Company company);
    public Contact save (Contact contact);
    Page<Contact> findAll(Pageable pageable);
    public void delete(Long id);
}
