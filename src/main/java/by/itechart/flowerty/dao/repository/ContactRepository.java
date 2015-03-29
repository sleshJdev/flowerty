package by.itechart.flowerty.dao.repository;

import org.springframework.data.repository.CrudRepository;

import by.itechart.flowerty.model.Contact;
/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 26.03.15
 * Time: 6:41
 * To change this template use File | Settings | File Templates.
 */

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
