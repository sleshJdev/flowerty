package by.itecharty.flowerty.solr.repository;

import by.itechart.flowerty.solr.repository.SolrContactRepository;
import by.itecharty.flowerty.config.JpaConfigurationAware;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Maria
 *         Date: 10.04.15
 */

@Ignore
public class TestSolrContactRepository extends JpaConfigurationAware {
    @Autowired (required = true)
    private SolrContactRepository contactRepository;

  /*  @Test
    public void saveContactToSolr() {
        SearcheableContact contact = new SearcheableContact();
        contact.setId(2l);
        contact.setName("TestSolrContactName");
        contact.setSurname("TestSolrContactSurname");
        contactRepository.save(contact);
    }   */
@Test
public void shouldWireRepository() {
    Assert.assertNotNull(contactRepository);
}
}
