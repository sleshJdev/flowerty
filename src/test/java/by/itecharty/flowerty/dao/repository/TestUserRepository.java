package by.itecharty.flowerty.dao.repository;

import by.itechart.flowerty.dao.repository.UserRepository;
import by.itechart.flowerty.model.Contact;
import by.itechart.flowerty.model.User;
import by.itecharty.flowerty.config.JpaConfigurationAware;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         Test for UserRepository
 */
 @Ignore
public class TestUserRepository extends JpaConfigurationAware {
	@Autowired
	private UserRepository userRepository;

	@Test
	public void findAll_ShouldReturnListOfUser() {
		// expected
		final String firstUserLogin = "test";
		final String secondUserLogin = "sergeM";

		Iterable<User> allUsers = userRepository.findAll();
		Assert.assertNotNull(allUsers);

		Assert.assertNotEquals(0, ((Collection<User>) allUsers).size());

		Iterator<User> i = allUsers.iterator();

		User first = i.next();
		Assert.assertEquals(firstUserLogin, first.getLogin());

		User second = i.next();
      //  List<User> list= userRepository.findByLoginContains("est");
      //  Assert.assertEquals(list.size(), 7);
		Assert.assertEquals(secondUserLogin, second.getLogin());
	}

	@Test
	public void getUserById_PassIdOfExistsUser_MustReturnTheCorrespondingUser() {
		// expected
		final Long id = 1L;
		final String login = "test";
		User user = userRepository.findOne(id);

		Assert.assertNotNull(user);
		Assert.assertEquals(id, user.getId());
		Assert.assertEquals(login, user.getLogin());
		Assert.assertEquals("Ivan", user.getContact().getName());
	}

	@Test
	public void getUserById_PassIdOfNotExistsUser_ShouldReturnNull() {
		// TODO: need implements
	}

	@Test
	public void exists_PassValidLoginAndPassword_ShoudReturnTrue() {
		// expected
		final String login = "sergeM";
		final String password = "sergeM";

		Boolean isExists = (userRepository.findUserByLoginAndPassword(login, password) != null);

		Assert.assertTrue(isExists);
	}

	@Test
	public void exists_PassInvalidLoginAndPassword_ShouldReturnFalse() {
		final String login = "sergeM";
		final String password = "sergeMM";

		Boolean isExists = (userRepository.findUserByLoginAndPassword(login, password) != null);

		Assert.assertFalse(isExists);
	}

	@Ignore
	@Test
	public void saveUser_ValidUser_ReturnsSameUser() {
		Contact contact = new Contact();
		contact.setId(1l);
		User user = new User();
		user.setLogin("testLogin");
		user.setPassword("testPassword");
		user.setContact(contact);
		user = userRepository.save(user);
	}

	@Ignore
	@Test
	public void deleteUser() {
		userRepository.delete(2l);
	}


    @Test
    public void  saveUser_InvalidUser_ThrowsException() {
        //   Address address = new Address();
        //   address.setId(2l);
        Contact contact = new Contact();
        contact.setId(1l);
        User user = new User();
        user.setLogin("testLogintoooooooooooooooooooooooooooooolonglogin");
        user.setPassword("testPassword");
        user.setContact(contact);
        try {
        user = userRepository.save(user);
        } catch (ExceptionInInitializerError ex) {
            return;
        }
        Assert.assertEquals(1,2);
    }
//    @Test
//    public void  deleteUser() {
//        userRepository.delete(2l);
//    }

	@Test
	public void findUserByLogin_PassValidLogin_MustReturnTheCorrespondingUser() {
		final String login = "test";

		User user = userRepository.findUserByLogin(login);

		Assert.assertNotNull(user);
		Assert.assertEquals(login, user.getLogin());
	}
}
