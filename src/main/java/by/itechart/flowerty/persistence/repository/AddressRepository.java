package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.repository.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
    public Address save(Address address);
    public void delete (Address address);
}
