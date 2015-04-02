package by.itecharty.flowerty.dao.repository;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import by.itechart.flowerty.dao.repository.UserRepository;
import by.itechart.flowerty.model.Contact;
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
	public void findAll_ShouldReturnListOfUser() {
		// expected
		final String firstUserLogin = "sergeM";
		final String secondUserLogin = "test";

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
	public void getUserById_PassIdOfExistsUser_ShouldReturnAccordingUser() {
		// expected
		final Long id = 1L;
		final String login = "sergeM";

		User user = userRepository.findOne(id);
		
		System.out.println(user.getRole().getName());

		Assert.assertNotNull("user cannot be equal null", user);
		Assert.assertEquals(String.format("user id must be %s", id), id, user.getId());
		Assert.assertEquals(String.format("user login must be %s", login), login, user.getLogin());
        Assert.assertEquals("Sergey", user.getContact().getName());
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
    public void exists_PassInvalidLoginAndPassword_ShoudReturnTrue() {
        final String login = "sergeM";
        final String password = "sergeMM";


        Boolean isExists = (userRepository.findUserByLoginAndPassword(login, password) != null);

        Assert.assertEquals(String.format("user with login: %s and password %s must not exists", login, password),
                Boolean.FALSE, isExists);
    }
    @Test
    public void  saveUser() {
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
//    @Test
//    public void  deleteUser() {
//        userRepository.delete(2l);
//    }
}
