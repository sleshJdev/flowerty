package by.itecharty.flowerty.web.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.springframework.http.MediaType;

import by.itechart.flowerty.model.Contact;
import by.itechart.flowerty.model.Phone;
import by.itechart.flowerty.model.Role;
import by.itechart.flowerty.model.User;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 * 
 *         Helper for test
 */
@Ignore
public final class TestControllerHelper {
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		return mapper.writeValueAsBytes(object);
	}

	public static String createStringWithLength(int length) {
		StringBuilder builder = new StringBuilder();

		for (int index = 0; index < length; index++) {
			builder.append("P");
		}

		return builder.toString();
	}

	public static User buildShortUserForTest() {
		User existsUser = new User();
		existsUser.setLogin("sergeM");
		existsUser.setPassword("sergeM");

		return existsUser;
	}

	public static User buildUserAdminForTest() {
		Role adminRole = new Role();
		adminRole.setName(Role.ROLE_TYPE.ADMIN);
		adminRole.setId(1L);

		Phone phoneAdmin = new Phone();
		phoneAdmin.setComment("admin nice comment");
		phoneAdmin.setCountry("belarus");
		phoneAdmin.setId(1L);
		phoneAdmin.setNumber("7788");
		phoneAdmin.setOperator("29");
		phoneAdmin.setType(Phone.PHONE_TYPE.HOME);

		Set<Phone> phonesAdmin = new HashSet<Phone>(1);
		phonesAdmin.add(phoneAdmin);

		final Long id1 = 1L;
		Contact adminContact = new Contact();
		adminContact.setId(id1);
		adminContact.setName("Petya");
		adminContact.setSurname("Pupkin");
		adminContact.setEmail("petya@mail.com");
		adminContact.setFathername("petrov");
		adminContact.setPhones(phonesAdmin);

		User admin = new User(id1, "admin", "adminpassword", adminRole, adminContact);

		return admin;
	}

	public static User buildUserManagerForTest() {
		Role delivetyManagerRole = new Role();
		delivetyManagerRole.setName(Role.ROLE_TYPE.DELIVERY_MANAGER);
		delivetyManagerRole.setId(2L);

		Phone phoneManager = new Phone();
		phoneManager.setComment("delivery nice comment");
		phoneManager.setCountry("belarus");
		phoneManager.setId(2L);
		phoneManager.setNumber("7788");
		phoneManager.setOperator("29");
		phoneManager.setType(Phone.PHONE_TYPE.CELL);

		Set<Phone> phonesManager = new HashSet<Phone>(1);

		phonesManager.add(phoneManager);

		final Long id2 = 2L;
		Contact managerContact = new Contact();
		managerContact.setId(id2);
		managerContact.setName("Vasya");
		managerContact.setSurname("Pupkin");
		managerContact.setEmail("vasya@mail.com");
		managerContact.setFathername("vaskin");
		managerContact.setPhones(phonesManager);

		User manager = new User(id2, "manager", "managerpassword", delivetyManagerRole, managerContact);

		return manager;
	}

	@SuppressWarnings("serial")
	public static Iterable<User> buildUserListForTest() {
		return new ArrayList<User>() {
			{
				add(buildUserAdminForTest());
				add(buildUserManagerForTest());
			}
		};
	}
}
