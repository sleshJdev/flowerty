package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.Company;
import by.itechart.flowerty.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {
    public Contact findOne(Long id);
    public Page<Contact> findByCompany(Company company, Pageable pageable);
    public Contact save (Contact contact);
    Page<Contact> findAll(Pageable pageable);
    public void delete(Long id);
    @Query("select c.name, c.surname, c.fathername, c.birthday, c.address from Contact c where c.company = ?1")
    List<Contact> getShortContacts (Company company);
}
