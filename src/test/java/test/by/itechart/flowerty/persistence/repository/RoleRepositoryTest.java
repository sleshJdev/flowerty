package test.by.itechart.flowerty.persistence.repository;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.by.itechart.flowerty.config.aware.JpaConfigurationAware;
import test.by.itechart.flowerty.persistence.repository.helper.RoleRepositoryHelperTest;
import by.itechart.flowerty.persistence.model.Role;
import by.itechart.flowerty.persistence.repository.RoleRepository;

/**
 * Created by Rostislav on 14-May-15
 */

public class RoleRepositoryTest extends JpaConfigurationAware {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void findByName_ShouldReturnAUser() {

        Role expected = RoleRepositoryHelperTest.getRoleWithIdOne();

        Role actual = roleRepository.findByName(Role.ROLE_TYPE.ADMIN);

        assertNotNull(actual);
        assertThat(actual, allOf(
                hasProperty("id", is(expected.getId())),
                hasProperty("name", is(expected.getName()))
        ));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void findAll_ShouldReturnAListOfAllRoles() {

        List<Role> roles = roleRepository.findAll();

        assertNotNull(roles);
        assertThat(roles.size(), is(5));
        assertThat(roles, contains(
                allOf(
                        hasProperty("id", is(1L)),
                        hasProperty("name", is(Role.ROLE_TYPE.ADMIN))
                ),
                allOf(
                        hasProperty("id", is(2L)),
                        hasProperty("name", is(Role.ROLE_TYPE.DELIVERY_MANAGER))
                ),
                allOf(
                        hasProperty("id", is(3L)),
                        hasProperty("name", is(Role.ROLE_TYPE.ORDERS_MANAGER))
                ),
                allOf(
                        hasProperty("id", is(4L)),
                        hasProperty("name", is(Role.ROLE_TYPE.ORDERS_PROCESSOR))
                ),
                allOf(
                        hasProperty("id", is(5L)),
                        hasProperty("name", is(Role.ROLE_TYPE.SUPERVISOR))
                )
        ));
    }
}
