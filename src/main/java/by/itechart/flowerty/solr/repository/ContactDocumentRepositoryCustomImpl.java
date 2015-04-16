package by.itechart.flowerty.solr.repository;

import by.itechart.flowerty.model.ContactDocument;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

/**
 * @author Maria
 *         Date: 16.04.15
 */
public class ContactDocumentRepositoryCustomImpl extends QueryDslRepositorySupport implements ContactDocumentRepositoryCustom {
    public ContactDocumentRepositoryCustomImpl() {
        super(ContactDocument.class);
    }

}
