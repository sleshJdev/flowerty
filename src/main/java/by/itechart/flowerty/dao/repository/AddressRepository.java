package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.Address;

import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
    @SuppressWarnings("unchecked")
    public Address save(Address address);

    public void delete(Address address);
}
