package test.by.itechart.flowerty.solr.repository;

import by.itechart.flowerty.solr.model.ContactDocument;
import by.itechart.flowerty.solr.repository.ContactDocumentRepository;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import test.by.itechart.flowerty.config.aware.JpaConfigurationAware;

import java.util.Date;

/**
* @author Maria
*         Date: 12.04.15
*/
@Ignore
public class SolrRepositoryTests extends JpaConfigurationAware {
    @Autowired
    private ContactDocumentRepository repository;


    @Test
    @Transactional
    public void findAll() {
        ContactDocument cd = new ContactDocument();
        cd.setId("4");
        cd.setName("TestName4");
        cd.setSurname("TestSurname4");
        cd.setBirthday(new Date(10, 10, 1990));
        cd.setCountry("TestCountry");
        cd.setTown("TestTown");
        cd.setHouse("12a");
        cd.setFlat("101");

        Page<ContactDocument> pg;
        cd = repository.save(cd);
        long a = repository.count();
    //    boolean exists123 = repository.exists("123");
   //     repository.findOne("123");
       // repository.findAll();
        pg = repository.findAll(new PageRequest(0, 10));
        Assert.assertEquals(pg.getContent().size(), 10);
    }
}
