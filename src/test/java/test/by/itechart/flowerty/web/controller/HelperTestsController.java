package test.by.itechart.flowerty.web.controller;

import java.io.IOException;
import java.nio.charset.Charset;

import org.joda.time.DateTime;
import org.springframework.http.MediaType;

import test.by.itechart.flowerty.persistence.repository.helper.ContactRepositoryHelperTests;
import by.itechart.flowerty.persistence.model.Contact;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *          
 *         Helper for test
 */
public final class HelperTestsController extends ContactRepositoryHelperTests {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper.writeValueAsBytes(object);
    }
    
    public static Contact getContactForRemoving(){
        Contact contact = getContactWithIdOne();
        contact.setId(13L);
        contact.setName("Eugene");
        contact.setSurname("Putsykovich");
        contact.setFathername("Aleksandrovich");
        contact.setBirthday(DateTime.parse("1993-01-01").toDate());
        contact.setEmail("eugene@mail.com");

        return contact;
    }
    
    public static Contact getContactForSaving(){
	Contact contact = getContactWithIdOne();
        contact.setName("New");
        contact.setSurname("Newov");
        contact.setFathername("Newich");
        contact.setBirthday(DateTime.parse("1999-01-01").toDate());
        contact.setEmail("new@mail.com");

        return contact;
    }
}
