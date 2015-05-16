package test.by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.User;
import by.itechart.flowerty.persistence.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.by.itechart.flowerty.config.aware.JpaConfigurationAware;
import test.by.itechart.flowerty.persistence.repository.helper.RoleRepositoryHelperTests;
import test.by.itechart.flowerty.persistence.repository.helper.UserRepositoryHelperTests;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Rostislav on 13-May-15
 */

public class UserRepositoryTest extends JpaConfigurationAware {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findUserByLoginAndPassword_ShouldReturnAUser() {

        User expected = UserRepositoryHelperTests.getUserWithIdOne();

        User actual = userRepository.findUserByLoginAndPassword("test", "$2a$10$ZWwh6S.iW5Sjeo2mklifkegHdSDOpmxpAw5oHDRTEMWgHLS.bILny");

        assertNotNull(actual);
        assertThat(actual.getId(), equalTo(1L));
        assertThat(actual, allOf(
                hasProperty("id", is(expected.getId())),
                hasProperty("login", is(expected.getLogin())),
                hasProperty("password", is(expected.getPassword())),
//                hasProperty("role", is(expected.getRole())),
                hasProperty("contact", is(expected.getContact()))
        ));
    }

    @Test
    public void findUserByLogin_ShouldReturnAUser() {

        User expected = UserRepositoryHelperTests.getUserWithIdOne();

        User actual = userRepository.findUserByLogin("test");

        assertNotNull(actual);
        assertThat(actual, allOf(
                hasProperty("id", is(expected.getId())),
                hasProperty("login", is(expected.getLogin())),
                hasProperty("password", is(expected.getPassword())),
//                hasProperty("role", is(expected.getRole())),
                hasProperty("contact", is(expected.getContact()))
        ));
    }

    @Test
    public void findOne_ShouldReturnAUser() {

        User expected = UserRepositoryHelperTests.getUserWithIdOne();

        User actual = userRepository.findOne(1L);

        assertNotNull(actual);
        assertThat(actual, allOf(
                hasProperty("id", is(expected.getId())),
                hasProperty("login", is(expected.getLogin())),
                hasProperty("password", is(expected.getPassword())),
//                hasProperty("role", is(expected.getRole())),
                hasProperty("contact", is(expected.getContact()))
        ));
    }

    @Test
    public void findByRole_ShouldReturnAListOfUsers() {

        List<User> users = userRepository.findByRole(RoleRepositoryHelperTests.getRoleWithIdOne());

        assertNotNull(users);
        assertThat(users.size(), is(2));
    }
}
