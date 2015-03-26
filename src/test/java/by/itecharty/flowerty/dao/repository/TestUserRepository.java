package by.itecharty.flowerty.dao.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import by.itechart.flowerty.dao.repository.UserRepository;
import by.itechart.flowerty.model.User;
import by.itecharty.flowerty.config.JpaConfigurationAware;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         Test for UserRepository
 */
public class TestUserRepository extends JpaConfigurationAware {
	@Autowired
	private UserRepository userRepository;

	@Test
	public void getUserById_PassIdOfExistsUser_ShouldReturnAccordingUser() {
		final String login = "sergeM";
		User user = userRepository.findOne(1L);
		Assert.assertEquals("user must be null", login, user.getLogin());
	}
	
	@Test
	public void exists_PassValidLoginAndPassword_ShoudReturnTrue(){
		final String login = "sergeM";
		final String password = "sergeM";
		Boolean isExists = (userRepository.existsByLoginAndPassword(login, password) != null);
		Assert.assertEquals(
				String.format("user with login: %s and password %s must be exists", login, password), 
				Boolean.TRUE, isExists);
	}
}
