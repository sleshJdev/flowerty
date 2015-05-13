package test.by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.User;
import by.itechart.flowerty.persistence.repository.UserRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.by.itechart.flowerty.config.aware.JpaConfigurationAware;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Rostislav on 13-May-15
 */

public class UserRepositoryTest extends JpaConfigurationAware {

    @Autowired
    private UserRepository userRepository;

    @Ignore
    @Test
    public void findUserByLogin() {

        User user = userRepository.findUserByLogin("test");

        assertThat(user.getId(), equalTo(1L));
    }
}
