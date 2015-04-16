package by.itechart.flowerty.web.service;

import by.itechart.flowerty.solr.repository.ContactDocumentRepository;
import by.itechart.flowerty.model.Contact;
import by.itechart.flowerty.model.ContactDocument;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author Maria
*         Date: 11.04.15
*/
@Service
public class RepositoryContactIndexService  {

    @Resource
    private ContactDocumentRepository repository;

    public void addToIndex(Contact contactEntry) {
        ContactDocument document = ContactDocument.getBuilder(contactEntry.getId(), contactEntry.getName())
                .surname(contactEntry.getSurname())
                .build();

        repository.save(document);
    }

    public void deleteFromIndex(Long id) {
        repository.delete(id.toString());
    }
}
