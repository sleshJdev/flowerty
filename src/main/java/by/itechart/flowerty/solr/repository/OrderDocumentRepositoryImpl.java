package by.itechart.flowerty.solr.repository;

import by.itechart.flowerty.solr.model.OrderDocument;
import com.mysql.jdbc.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maria
 *         Date: 02.05.15
 */
@Repository
public class OrderDocumentRepositoryImpl implements OrderDocumentRepositoryCustom{

    @Resource
    private SolrTemplate solrTemplatePurchase;

    @Override
    public List<Long> findBySearch(OrderDocument orderDocument) {
        Criteria criteria = null;
        if (!StringUtils.isNullOrEmpty(orderDocument.getCustomer())) {
            criteria = new Criteria("customer").contains(orderDocument.getCustomer());
        }
        if (!StringUtils.isNullOrEmpty(orderDocument.getReceiver())) {
            if (criteria == null) {
                criteria = new Criteria("receiver").contains(orderDocument.getReceiver());
            } else {
                criteria = criteria.and(new Criteria("receiver").contains(orderDocument.getReceiver()));
            }
        }

        if (orderDocument.getDeliveryDateAfter() != null) {
            if (criteria == null) {
                criteria = new Criteria("deliveryDate").greaterThan(orderDocument.getDeliveryDateAfter());
            } else {
                criteria = criteria.and(new Criteria("deliveryDate").greaterThan(orderDocument.getDeliveryDateAfter()));
            }
        }
        if (orderDocument.getDeliveryDateBefore() != null) {
            if (criteria == null) {
                criteria = new Criteria("deliveryDate").lessThan(orderDocument.getDeliveryDateBefore());
            } else {
                criteria = criteria.and(new Criteria("deliveryDate").lessThan(orderDocument.getDeliveryDateBefore()));
            }
        }

        if (criteria == null) {
            return null;
        }
        SimpleQuery query = new SimpleQuery(criteria);

        Page results = solrTemplatePurchase.queryForPage(query, OrderDocument.class);
        List<Long> list = new ArrayList<Long>();
        for (OrderDocument od: (List<OrderDocument>)results.getContent()) {
            list.add(Long.valueOf(od.getId()));
        }
        return list;
    }
}
