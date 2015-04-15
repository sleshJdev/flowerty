package by.itechart.flowerty.web.service;

import by.itechart.flowerty.dao.repository.ContactDocumentRepository;
import by.itechart.flowerty.model.Contact;
import by.itechart.flowerty.model.ContactDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author Maria
 *         Date: 11.04.15
 */
@Service
public class RepositorySolrContactService {//implements SolrContactService {
 //   @Resource
    @Autowired
    private RepositoryContactIndexService indexService;

   // @Resource
    @Autowired
    private ContactDocumentRepository repository;

 //   @PreAuthorize("hasPermission('Contact', 'add')")
    @Transactional
    public ContactDocument add(Contact added) {
        ContactDocument model = ContactDocument.getBuilder(added.getId(), added.getName())
                .surname(added.getSurname())
                .build();

        ContactDocument persisted = repository.save(model);
        indexService.addToIndex(added);
      //  Contact contact = new Contact(); //create it by persisted
       // indexService.addToIndex(contact);
        return persisted;
    }

 //   @PreAuthorize("hasPermission('Contact', 'delete')")
//    @Transactional
//    public Contact deleteById(Long id) {
//        Contact deleted = findById(id);
//
//        repository.delete(deleted);
//        indexService.deleteFromIndex(id);
//
//        return deleted;
//    }
      public List<ContactDocument> findByNameContains(String name) {
          return repository.findByNameContains(name);
      }
   /* @PreAuthorize("hasPermission('Contact', 'update')")
    @Transactional
    @Override
    public Contact update(Contact updated) {
        //Todo model = findById(updated.getId());
      //  repository.save(updated.buildContactDocument());
        //model.update(updated.getDescription(), updated.getTitle());
     //   Contact contact = new Contact(); //createByUpdated
     //   indexService.addToIndex(updated);

        return updated;
    }    */
  //  @PreAuthorize("hasPermission('Contact', 'search')")
    //@Override
   // public List<ContactDocument> search(String searchTerm) {
     //   return indexService.search}
}
