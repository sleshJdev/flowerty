package by.itechart.flowerty.solr.repository;

import by.itechart.flowerty.solr.model.OrderDocument;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

/**
 * @author Maria
 *         Date: 02.05.15
 */
public interface OrderDocumentRepository extends SolrCrudRepository<OrderDocument, String>, OrderDocumentRepositoryCustom{
    public List<OrderDocument> findById(String id);
}
