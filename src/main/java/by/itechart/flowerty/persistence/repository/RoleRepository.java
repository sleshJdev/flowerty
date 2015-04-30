package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.repository.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 02.04.15
 * Time: 20:46
 * To change this template use File | Settings | File Templates.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    public List<Role> findAll();
    public Role findByName(String name);
}
