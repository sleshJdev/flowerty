package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.Company;
import by.itechart.flowerty.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {
    public Contact findOne(Long id);
    public Page<Contact> findByCompany(Company company, Pageable pageable);
    public Contact save (Contact contact);
  //  Page<Contact> findAll(Pageable pageable);
    public void delete(Long id);
}
