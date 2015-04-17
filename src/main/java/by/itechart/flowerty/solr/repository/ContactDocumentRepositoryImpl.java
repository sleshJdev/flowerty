package by.itechart.flowerty.solr.repository;

import by.itechart.flowerty.solr.model.ContactDocument;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;

import javax.annotation.Resource;
import java.util.List;
/**
 * @author Maria
 *         Date: 16.04.15
 */
@NoRepositoryBean
public class ContactDocumentRepositoryImpl implements ContactDocumentRepositoryCustom {
  // private static final QContactDocument QD_BOOK = QCo

    @Resource
    private SolrTemplate solrTemplate;


    @Override
    public List<Long> findByBirthDate(String birthday) {
        LocalDate date = null;
   /*     try {
            date = new LocalDate(DateUtils.parseDate(birthday)) ;
        }  catch (DateParseException dateParseException) {
            return null;  //To change body of catch statement use File | Settings | File Templates.
        }     */
        Criteria criteria = new Criteria("birthday.DAY").is(date.dayOfMonth())
                .and(new Criteria("birthday.MONTH").is(date.monthOfYear()));
        SimpleQuery search = new SimpleQuery(criteria);
        Page results = solrTemplate.queryForPage(search, ContactDocument.class);
        return results.getContent();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ContactDocument> findBySearch(ContactDocument contactDocument) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
