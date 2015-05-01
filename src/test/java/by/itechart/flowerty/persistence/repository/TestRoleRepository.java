package by.itechart.flowerty.persistence.repository;
 

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import by.itechart.flowerty.config.aware.JpaConfigurationAware;
import by.itechart.flowerty.persistence.model.Role;

/**
 * @author  Мария 02.04.15
 */
@Ignore
public class TestRoleRepository extends JpaConfigurationAware {
    @Autowired
    private RoleRepository roleRepository;
    @Ignore
    @Test
    public void findAll_ReturnsListOfAllRoles() {
        List<Role> list = roleRepository.findAll();
        Assert.assertEquals(list.size(), 5);
    }
    
    
}
