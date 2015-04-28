package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.Company;
import by.itechart.flowerty.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Long>, ContactRepositoryCustom {
    public Contact findOne(Long id);
    public Page<Contact> findByCompany(Company company, Pageable pageable);
    public Contact save (Contact contact);
    public Page<Contact> findByIdIsIn(List<Long> list, Pageable pageable);
    public List<Contact> findByIdIn(List<Long> list);
   // public List<Contact> findByIdNotIn(List<Long> list);
  //  Page<Contact> findAll(Pageable pageable);
    public void delete(Long id);
}
