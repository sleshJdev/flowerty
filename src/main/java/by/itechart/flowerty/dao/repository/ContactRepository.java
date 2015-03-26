package by.itechart.flowerty.dao.repository;

import org.springframework.data.repository.CrudRepository;

import by.itechart.flowerty.model.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
