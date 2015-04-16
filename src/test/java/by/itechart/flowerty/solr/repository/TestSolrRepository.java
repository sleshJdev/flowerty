//package by.itechart.flowerty.solr.repository;
//
//import by.itechart.flowerty.config.JpaConfigurationAware;
//import by.itechart.flowerty.dao.repository.ContactDocumentRepository;
//import by.itechart.flowerty.model.ContactDocument;
//import org.junit.Assert;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//
///**
// * @author Maria
// *         Date: 12.04.15
// */
//@Ignore
//public class TestSolrRepository extends JpaConfigurationAware {
//    @Autowired
//    private ContactDocumentRepository repository;
//
//
//    @Test
//   // @Transactional
//    public void findAll() {
//        ContactDocument cd = new ContactDocument();
//        cd.setId("3");
//        cd.setName("TestName3");
//        cd.setSurname("TestSurname3");
//        Page<ContactDocument> pg;
//        cd = repository.save(cd);
//        long a = repository.count();
//    //    boolean exists123 = repository.exists("123");
//   //     repository.findOne("123");
//        repository.findAll();
//        pg = repository.findAll(new PageRequest(0, 10));
//        Assert.assertEquals(pg.getContent().size(), 3);
//    }
//}
