package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.Role;
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
}
