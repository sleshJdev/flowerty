package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * User: Мария Date: 02.04.15 Time: 20:46
 * 
 */
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    public List<Role> findAll();

    public Role findByName(Role.ROLE_TYPE name);
}
