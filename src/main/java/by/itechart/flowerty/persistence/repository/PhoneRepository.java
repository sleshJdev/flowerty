package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.repository.model.Phone;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PhoneRepository extends PagingAndSortingRepository<Phone, Long> {
    public Phone findOne(Long id);
    public Phone save (Phone phone);
    public void delete(Long id);
   // public Page<Phone> findByContact(Contact contact, Pageable pageable);
}
