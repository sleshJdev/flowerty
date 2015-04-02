package by.itecharty.flowerty.dao.repository;

import by.itechart.flowerty.dao.repository.RoleRepository;
import by.itechart.flowerty.model.Role;
import by.itecharty.flowerty.config.JpaConfigurationAware;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 02.04.15
 * Time: 20:48
 * To change this template use File | Settings | File Templates.
 */
public class TestRoleRepository extends JpaConfigurationAware {
    @Autowired
    private RoleRepository roleRepository;
    @Test
    public void findAll_ReturnsListOfAllRoles() {
        List<Role> list = roleRepository.findAll();
        Assert.assertEquals(list.size(), 5);
    }
}
