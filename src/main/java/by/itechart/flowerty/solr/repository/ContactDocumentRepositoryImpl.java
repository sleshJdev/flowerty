package by.itechart.flowerty.solr.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Repository;

import by.itechart.flowerty.persistence.model.Address;
import by.itechart.flowerty.solr.model.ContactDocument;

import com.mysql.jdbc.StringUtils;

/**
 * @author Maria Date: 16.04.15
 */
@Repository
public class ContactDocumentRepositoryImpl implements ContactDocumentRepositoryCustom {
    // private static final QContactDocument QD_BOOK = QCo

    @Resource
    private SolrTemplate solrTemplateContact;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<Long> findByBirthDate(String birthday) {
	LocalDate date = null;
	try {
	    date = new LocalDate(DateUtils.parseDate(birthday, "yyyy-MM-dd"));
	} catch (Exception exception) {
	    return null; // To change body of catch statement use File |
			 // Settings | File Templates.
	}

	Criteria criteria = new Criteria("month").is(date.getMonthOfYear());
	criteria = criteria.and(new Criteria("day").is(date.getDayOfMonth()));
	SimpleQuery search = new SimpleQuery(criteria);
	Page results = solrTemplateContact.queryForPage(search, ContactDocument.class);
	return results.getContent();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
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
	if (!StringUtils.isNullOrEmpty(contactDocument.getFathername())) {
	    if (criteria == null) {
		criteria = new Criteria("fathername").contains(contactDocument.getSurname());
	    } else {
		criteria = criteria.and(new Criteria("fathername").contains(contactDocument.getSurname()));
	    }
	}
	if (contactDocument.getDay() != null) {
	    if (criteria == null) {
		criteria = new Criteria("day").is(contactDocument.getDay());
	    } else {
		criteria = criteria.and(new Criteria("day").is(contactDocument.getDay()));
	    }
	}
	if (contactDocument.getMonth() != null) {
	    if (criteria == null) {
		criteria = new Criteria("month").is(contactDocument.getMonth());
	    } else {
		criteria = criteria.and(new Criteria("month").is(contactDocument.getMonth()));
	    }
	}
	if (contactDocument.getYear() != null) {
	    if (criteria == null) {
		criteria = new Criteria("year").is(contactDocument.getYear());
	    } else {
		criteria = criteria.and(new Criteria("year").is(contactDocument.getYear()));
	    }
	}
	if (contactDocument.getBirthdayAfter() != null) {
	    if (criteria == null) {
		criteria = new Criteria("birthday").greaterThan(contactDocument.getBirthdayAfter());
	    } else {
		criteria = criteria.and(new Criteria("birthday").greaterThan(contactDocument.getBirthdayAfter()));
	    }
	}
	if (contactDocument.getBirthdayBefore() != null) {
	    if (criteria == null) {
		criteria = new Criteria("birthday").lessThan(contactDocument.getBirthdayBefore());
	    } else {
		criteria = criteria.and(new Criteria("birthday").lessThan(contactDocument.getBirthdayBefore()));
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
	if (criteria == null) {
	    return null;
	}
	SimpleQuery query = new SimpleQuery(criteria);

	Page results = solrTemplateContact.queryForPage(query, ContactDocument.class);
	List<Long> list = new ArrayList<Long>();
	for (ContactDocument cd : (List<ContactDocument>) results.getContent()) {
	    list.add(Long.valueOf(cd.getId()));
	}
	return list;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<Long> findBySurnameStartsWithAndCompany(String surname, Long company) {
	Criteria criteria = new Criteria("surname").startsWith(surname);
	criteria = criteria.and(new Criteria("company").is(company));// .is(date.dayOfMonth())
	SimpleQuery search = new SimpleQuery(criteria);
	Page results = solrTemplateContact.queryForPage(search, ContactDocument.class);
	List<Long> list = new ArrayList<Long>();
	list.add(0L);
	for (ContactDocument cd : (List<ContactDocument>) results.getContent()) {
	    list.add(Long.valueOf(cd.getId()));
	}
	return list;
    }

    @Override
    public Long findByAddress(Address address) {

	return null;  
    }

    @Override
    public void deleteIdIsIn(List<String> ids) {
	solrTemplateContact.deleteById(ids);
    }
}
