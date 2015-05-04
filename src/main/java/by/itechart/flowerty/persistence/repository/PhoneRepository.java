package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Phone;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PhoneRepository extends PagingAndSortingRepository<Phone, Long> {
    public Phone findOne(Long id);
  }
