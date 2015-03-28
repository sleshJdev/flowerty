package by.itechart.flowerty.dao.repository;

import org.springframework.data.repository.CrudRepository;

import by.itechart.flowerty.model.Address;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AddressRepository extends CrudRepository<Address, Long> {
    public Address save(Address address);
    public void delete (Address address);
}
