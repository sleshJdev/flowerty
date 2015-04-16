package by.itechart.flowerty.solr.repository;

import by.itechart.flowerty.model.ContactDocument;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author Maria
*         Date: 11.04.15
*/
@Repository
public interface ContactDocumentRepository extends SolrCrudRepository<ContactDocument, String>, ContactDocumentRepositoryCustom {
    public List<ContactDocument> findByNameContains(String name);
}
