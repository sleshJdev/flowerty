package test.by.itechart.flowerty.persistence.repository;
 

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.by.itechart.flowerty.config.aware.JpaConfigurationAware;
import by.itechart.flowerty.persistence.model.Role;
import by.itechart.flowerty.persistence.repository.RoleRepository;

/**
 * @author  Мария 02.04.15
 */
@Ignore
public class RoleRepositoryTests extends JpaConfigurationAware {
    @Autowired
    private RoleRepository roleRepository;
    @Ignore
    @Test
    public void findAll_ReturnsListOfAllRoles() {
        List<Role> list = roleRepository.findAll();
        Assert.assertEquals(list.size(), 5);
    }
    
    
}
