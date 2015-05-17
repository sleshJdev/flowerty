package by.itechart.flowerty.solr.repository;

import by.itechart.flowerty.solr.model.ContactDocument;

import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

/**
 * @author Maria Date: 11.04.15
 */
public interface ContactDocumentRepository extends SolrCrudRepository<ContactDocument, String>,
	ContactDocumentRepositoryCustom {
    public List<ContactDocument> findByNameContains(String name);

    public List<ContactDocument> findBySurnameContains(String surname);

    public List<ContactDocument> findByNameOrSurnameAllIgnoreCase(String name, String surname);
}
