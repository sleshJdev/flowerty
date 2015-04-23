package by.itechart.flowerty.solr.repository;

import by.itechart.flowerty.model.Contact;
import by.itechart.flowerty.solr.model.ContactDocument;
import com.mysql.jdbc.StringUtils;
import org.apache.solr.schema.TrieDateField;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.apache.commons.lang3.time.DateUtils;

import javax.annotation.Resource;
import java.util.*;
import org.springframework.stereotype.Repository;
/**
 * @author Maria
 *         Date: 16.04.15
 */
@Repository
public class ContactDocumentRepositoryImpl implements ContactDocumentRepositoryCustom {
  // private static final QContactDocument QD_BOOK = QCo

    @Resource
    private SolrTemplate solrTemplate;

    @Override
    public List<Long> findByBirthDate(String birthday) {
        LocalDate date = null;
        try {
            date = new LocalDate(DateUtils.parseDate(birthday, "yyyy-MM-dd")) ;
        }  catch (Exception exception) {
            return null;  //To change body of catch statement use File | Settings | File Templates.
        }

        Criteria criteria = new Criteria("birthday.DAY") .is(date.dayOfMonth())
                .and(new Criteria("birthday.MONTH").is(date.monthOfYear()));
        TrieDateField dateField = new TrieDateField();
       // criteria.
        SimpleQuery search = new SimpleQuery(criteria);
        Page results = solrTemplate.queryForPage(search, ContactDocument.class);
        return results.getContent();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Long> findBySearch(ContactDocument contactDocument) {
        Criteria criteria = null;
        if (!StringUtils.isNullOrEmpty(contactDocument.getName())) {
            criteria = new Criteria("name").contains(contactDocument.getName());
        }
        if (!StringUtils.isNullOrEmpty(contactDocument.getSurname())) {
            if (criteria == null) {
                criteria = new Criteria("surname").contains(contactDocument.getSurname());
            } else {
            criteria = criteria.and(new Criteria("surname").contains(contactDocument.getSurname()));
            }
        }
        if (!StringUtils.isNullOrEmpty(contactDocument.getCountry())) {
            if (criteria == null) {
                criteria = new Criteria("country").contains(contactDocument.getCountry());
            } else {
                criteria = criteria.and(new Criteria("country").contains(contactDocument.getCountry()));
            }
        }
        if (!StringUtils.isNullOrEmpty(contactDocument.getTown())) {
            if (criteria == null) {
                criteria = new Criteria("town").contains(contactDocument.getTown());
            } else {
                criteria = criteria.and(new Criteria("town").contains(contactDocument.getTown()));
            }
        }
        if (!StringUtils.isNullOrEmpty(contactDocument.getStreet())) {
            if (criteria == null) {
                criteria = new Criteria("street").contains(contactDocument.getStreet());
            } else {
                criteria = criteria.and(new Criteria("street").contains(contactDocument.getStreet()));
            }
        }
        if (!StringUtils.isNullOrEmpty(contactDocument.getHouse())) {
            if (criteria == null) {
                criteria = new Criteria("house").contains(contactDocument.getHouse());
            } else {
                criteria = criteria.and(new Criteria("house").contains(contactDocument.getHouse()));
            }
        }
        if (!StringUtils.isNullOrEmpty(contactDocument.getFlat())) {
            if (criteria == null) {
                criteria = new Criteria("flat").contains(contactDocument.getFlat());
            } else {
                criteria = criteria.and(new Criteria("flat").contains(contactDocument.getFlat()));
            }
        }

        SimpleQuery query = new SimpleQuery(criteria);

        Page results = solrTemplate.queryForPage(query, ContactDocument.class);
        List<Long> list = new ArrayList<Long>();
        for (ContactDocument cd: (List<ContactDocument>)results.getContent()) {
            list.add(Long.valueOf(cd.getId()));
        }
        return list;
    }
}
