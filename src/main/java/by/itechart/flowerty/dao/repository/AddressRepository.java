package by.itechart.flowerty.dao.repository;

import org.springframework.data.repository.CrudRepository;

import by.itechart.flowerty.model.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
