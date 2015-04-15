package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.ContactDocument;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;
/**
 * @author Maria
 *         Date: 11.04.15
 */
//@Repository
public interface ContactDocumentRepository extends SolrCrudRepository<ContactDocument, String> {
    public List<ContactDocument> findByNameContains(String name);
}
