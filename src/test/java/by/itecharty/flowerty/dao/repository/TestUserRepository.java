package by.itecharty.flowerty.dao.repository;

import by.itechart.flowerty.dao.repository.UserRepository;
import by.itechart.flowerty.model.Contact;
import by.itechart.flowerty.model.User;
import by.itecharty.flowerty.config.JpaConfigurationAware;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         Test for UserRepository
 */

public class TestUserRepository extends JpaConfigurationAware {
	@Autowired
	private UserRepository userRepository;

	@Test
	public void findAll_ShouldReturnListOfUser() {
		// expected
		final String firstUserLogin = "test";
		final String secondUserLogin = "sergeM";

		Iterable<User> allUsers = userRepository.findAll();
		Assert.assertNotNull("user list cannot be null", allUsers);

        Assert.assertNotEquals(0, ((Collection<User>) allUsers).size());

		Iterator<User> i = allUsers.iterator();
		User first = i.next();
		Assert.assertEquals(String.format("first user login must be eqaul %s", firstUserLogin), firstUserLogin,
				first.getLogin());
		User second = i.next();
		Assert.assertEquals(String.format("second user login must be eqaul %s", secondUserLogin), secondUserLogin,
				second.getLogin());
	}
	
	@Test
	public void getUserById_PassIdOfExistsUser_MustReturnTheCorrespondingUser() {
		// expected
		final Long id = 2L;
		final String login = "sergeM";
		User user = userRepository.findOne(id);

		Assert.assertNotNull("user cannot be equal null", user);
		Assert.assertEquals(String.format("user id must be %s", id), id, user.getId());
		Assert.assertEquals(String.format("user login must be %s", login), login, user.getLogin());
        Assert.assertEquals("Anton", user.getContact().getName());
	}
    @Test
    public void getUserById_PassIdOfNotExistsUser_ShouldReturnNull() {
        final Long id = 100l;
       // final String login = "sergeM";

        User user = userRepository.findOne(id);

        Assert.assertNull("user must equal null", user);
       // Assert.assertEquals(String.format("user id must be %s", id), id, user.getId());
       // Assert.assertEquals(String.format("user login must be %s", login), login, user.getLogin());
    }

	@Test
	public void exists_PassValidLoginAndPassword_ShoudReturnTrue() {
		// expected
		final String login = "sergeM";
		final String password = "sergeM";


		Boolean isExists = (userRepository.findUserByLoginAndPassword(login, password) != null);

		Assert.assertEquals(String.format("user with login: %s and password %s must be exists", login, password),
				Boolean.TRUE, isExists);
	}
    @Test
    public void exists_PassInvalidLoginAndPassword_ShouldReturnFalse() {
        final String login = "sergeM";
        final String password = "sergeMM";


        Boolean isExists = (userRepository.findUserByLoginAndPassword(login, password) != null);

        Assert.assertEquals(String.format("user with login: %s and password %s must not exists", login, password),
                Boolean.FALSE, isExists);
    }
    @Test
    public void  saveUser_ValidUser_ReturnsSameUser() {
     //   Address address = new Address();
     //   address.setId(2l);
        Contact contact = new Contact();
        contact.setId(1l);
        User user = new User();
        user.setLogin("testLogin");
        user.setPassword("testPassword");
        user.setContact(contact);
        user = userRepository.save(user);
  //      Assert.assertEquals(user.getLogin(), "TestLogin");
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
	public void findUserByLogin_PassValidLogin_MustReturnTheCorrespondingUser(){
		final String login = "test";

		User user = userRepository.findUserByLogin(login);

		Assert.assertNotNull("user cannot be equal null", user);
		Assert.assertEquals(String.format("user login must be %s", login), login, user.getLogin());
	}
}
