package by.itechart.flowerty.solr.repository;

import by.itechart.flowerty.solr.model.OrderDocument;

import java.util.List;

/**
 * @author Maria
 *         Date: 02.05.15
 */
public interface OrderDocumentRepositoryCustom {
    public List<Long> findBySearch(OrderDocument orderDocument);
}
