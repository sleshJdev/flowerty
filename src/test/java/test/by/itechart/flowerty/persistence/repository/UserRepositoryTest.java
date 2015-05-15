package test.by.itechart.flowerty.persistence.repository;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.by.itechart.flowerty.config.aware.JpaConfigurationAware;
import by.itechart.flowerty.persistence.model.User;
import by.itechart.flowerty.persistence.repository.UserRepository;

/**
 * Created by Rostislav on 13-May-15
 */

public class UserRepositoryTest extends JpaConfigurationAware {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findUserByLoginAndPassword_ShouldReturnAUser() {

        User expected = HelperTestsRepository.getUserWithIdOne();

        User actual = userRepository.findUserByLoginAndPassword("test", "cf9ba7b8884c4431fe5cf83336a021df");

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

        User expected = HelperTestsRepository.getUserWithIdOne();

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
    public void findOne_findUserByLogin_ShouldReturnAUser() {

        User expected = HelperTestsRepository.getUserWithIdOne();

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

        List<User> users = userRepository.findByRole(HelperTestsRepository.getRoleWithIdOne());

        assertNotNull(users);
        assertThat(users.size(), is(2));
    }
}
