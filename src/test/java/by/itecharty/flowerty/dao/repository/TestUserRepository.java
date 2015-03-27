package by.itecharty.flowerty.dao.repository;

import by.itechart.flowerty.dao.repository.UserRepository;
import by.itechart.flowerty.model.User;
import by.itecharty.flowerty.config.JpaConfigurationAware;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         Test for UserRepository
 */

public class TestUserRepository extends JpaConfigurationAware {
	@Autowired
	private UserRepository userRepository;

//	@Test
//	public void getUserById_PassIdOfExistsUser_ShouldReturnAccordingUser() {
//		final String login = "sergeM";
//		User user = userRepository.findOne(1L);
//		Assert.assertEquals("user must be null", login, user.getLogin());
//	}
//
	@Test
	public void exists_PassValidLoginAndPassword_ShouldReturnTrue(){
		final String login = "sergeM";
		final String password = "sergeM";
		Boolean isExists = (userRepository.findUserByLoginAndPassword(login, password) != null);
		Assert.assertEquals(
				String.format("user with login: %s and password %s must be exists", login, password),
				Boolean.TRUE, isExists);
	}
    @Test
    public void exists_PassInvalidLoginAndPassword_ShouldReturnFalse(){
        final String login = "sergeM";
        final String password = "sergeM2";
        Boolean isExists = (userRepository.findUserByLoginAndPassword(login, password) != null);
        Assert.assertEquals(
                String.format("user with login: %s and password %s must be exists", login, password),
                Boolean.FALSE, isExists);
    }
    @Test
    public void testFindByGoodId() {
        User user = userRepository.findOne(1l);
        Assert.assertNotNull(user);
        Assert.assertEquals("sergeM", user.getLogin());
        Assert.assertEquals("sergeM", user.getPassword());
        Assert.assertEquals(Long.valueOf(2), user.getContact().getId());
        Assert.assertEquals(Long.valueOf(2), user.getRole().getId());
    }
    @Test
    public void testFindByBadId() {
        User user = userRepository.findOne(1000l);
        Assert.assertNull(user);
    }
    @Test
    public void testGetAllUsersOfCompanyGoodId() {
        User user = userRepository.findOne(1l);
        Assert.assertNotNull(user);
        Assert.assertEquals("sergeM", user.getLogin());
        Assert.assertEquals("sergeM", user.getPassword());
        Assert.assertEquals(Long.valueOf(2), user.getContact().getId());
        Assert.assertEquals(Long.valueOf(2), user.getRole().getId());
    }

}
