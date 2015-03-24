package by.itecharty.flowerty.dao.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import by.itechart.flowerty.dao.repository.UserRepository;
import by.itechart.flowerty.model.User;
import by.itecharty.flowerty.config.JpaConfigurationAware;

public class TestUserRepository extends JpaConfigurationAware{
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetUserById() {
	User user = userRepository.findOne(1L);
	Assert.assertNull("User must be null", user);
    }
}
