package by.itecharty.flowerty.solr.repository;

import by.itechart.flowerty.model.Contact;
import by.itechart.flowerty.model.User;
import by.itechart.flowerty.dao.repository.SolrUserRepository;
import by.itecharty.flowerty.config.JpaConfigurationAware;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Maria
 *         Date: 06.04.15
 */
@Ignore
public class TestSolrUserRepository extends JpaConfigurationAware {
   @Autowired
   private SolrUserRepository userRepository;

    @Test
    public void saveContactToSolr() {
        Contact contact = new Contact();
        contact.setId(2l);
        User user = new User();
        user.setContact(contact);
        user.setLogin("testSolrLogin");
        user.setPassword("testSolrPassword");
        user.setId(10l);
        userRepository.save(user);
    }

    @Test
    public void search() {
        String login = "est";
        List<User> list = userRepository.findByLoginContains(login);
        Assert.assertNotNull(list);
    }

}
