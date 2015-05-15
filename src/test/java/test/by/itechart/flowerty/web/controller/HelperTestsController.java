package test.by.itechart.flowerty.web.controller;

import by.itechart.flowerty.persistence.model.Contact;
import by.itechart.flowerty.persistence.model.Phone;
import by.itechart.flowerty.persistence.model.Role;
import by.itechart.flowerty.persistence.model.User;
import by.itechart.flowerty.web.model.UserEditBundle;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *         <p/>
 *         Helper for test
 */
@SuppressWarnings("serial")
public final class HelperTestsController {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper.writeValueAsBytes(object);
    }

    public static User buildValidShortUserForTest() {
        User existsUser = new User();
        existsUser.setLogin("admin");
        existsUser.setPassword("admin");

        return existsUser;
    }

    public static User buildInvalideShordUserForTest() {
        User notExistsUser = new User();
        notExistsUser.setLogin("iamnotexists");
        notExistsUser.setPassword("fk2");

        return notExistsUser;
    }

    public static Phone buildPhone() {
        Phone phone = new Phone();
        phone.setComment("delivery nice comment");
        phone.setCountry("belarus");
        phone.setNumber("7788");
        phone.setOperator("29");
        phone.setType(Phone.PHONE_TYPE.CELL);

        return phone;
    }

    public static Role buildAdminRole() {
        Role adminRole = new Role();
        adminRole.setName(Role.ROLE_TYPE.ADMIN);
        adminRole.setId(1L);

        return adminRole;
    }

    public static User buildUserAdminForTest() {
        User admin = new User(1L, "admin", "adminpassword", buildAdminRole(), buildContact());

        return admin;
    }

    public static Contact buildContact() {
        Contact contact = new Contact();
        contact.setName("Vasya");
        contact.setSurname("Pupkin");
        contact.setEmail("vasya@mail.com");
        contact.setFathername("vaskin");
        contact.setPhones(new HashSet<Phone>() {
            {
                add(buildPhone());
            }
        });

        return contact;
    }

    public static UserEditBundle buildUserEditBundleForTest() {
        UserEditBundle bundle = new UserEditBundle();
        bundle.setUser(buildUserAdminForTest());
        bundle.setRoles(new ArrayList<Role>() {{
                add(buildAdminRole());
            }
        });
        bundle.setContacts(new ArrayList<Contact>() {
            {
                add(buildContact());
            }
        });

        return bundle;
    }
}
