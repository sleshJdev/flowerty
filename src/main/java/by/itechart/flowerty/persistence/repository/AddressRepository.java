package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
    @SuppressWarnings("unchecked")
    public Address save(Address address);

    public void delete(Address address);
}
