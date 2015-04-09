package by.itecharty.flowerty.dao.repository;

import by.itechart.flowerty.dao.repository.RoleRepository;
import by.itechart.flowerty.model.Role;
import by.itecharty.flowerty.config.JpaConfigurationAware;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author  Мария 02.04.15
 */
@Ignore
public class TestRoleRepository extends JpaConfigurationAware {
    @Autowired
    private RoleRepository roleRepository;
    @Test
    public void findAll_ReturnsListOfAllRoles() {
        List<Role> list = roleRepository.findAll();
        Assert.assertEquals(list.size(), 5);
    }
}
