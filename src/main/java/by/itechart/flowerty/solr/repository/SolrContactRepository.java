package by.itechart.flowerty.solr.repository;

import by.itechart.flowerty.model.SearcheableContact;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Maria
 *         Date: 10.04.15
 */
@Repository
public interface SolrContactRepository extends SolrCrudRepository<SearcheableContact, String> {
public List<SearcheableContact> findByNameContains(String name);
}
