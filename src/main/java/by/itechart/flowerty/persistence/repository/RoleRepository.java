package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 02.04.15
 * Time: 20:46
 * To change this template use File | Settings | File Templates.
 */
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    public List<Role> findAll();
    public Role findByName(Role.ROLE_TYPE name);
}
