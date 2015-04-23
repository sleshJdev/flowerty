package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.Contact;
import com.mysema.query.jpa.impl.JPAQuery;
import org.hibernate.jpa.criteria.CriteriaDeleteImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import java.util.*;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Maria
 *         Date: 17.04.15
 */
//@NoRepositoryBean
 //@Repository
public class ContactRepositoryImpl implements ContactRepositoryCustom {   //extends QueryDslRepositorySupport
   @Autowired
   private EntityManager em;
   // private static final QContact Q_ID = QContact.contact.id;// .contact;

  /*  public ContactRepositoryImpl() {
        super(Contact.class);
    }       */
    @Override
    @Transactional
    public int deleteIdNotIn(List<Long> list) {

       // CriteriaBuilder builder = em.getCriteriaBuilder();
       // CriteriaDelete delete = builder.createCriteriaDelete(Contact.class);
       // Root contact = delete.from(Contact.class);
        // delete.from(Contact.class);
        // delete.from(QContact.class);// .from(Employee.class);
 //       delete.where(builder.not(contact.get("id").in(builder.parameter(List.class, "list"))) );
        int rowCount = em.createQuery("DELETE FROM Contact c WHERE c.id NOT IN :list").setParameter("list",list).executeUpdate();
      //  Query query = em.createQuery(delete);
        //int rowCount = query.executeUpdate();
       // Query query = em.createQuery()
        //JPAQuery query = new JPAQuery(em);
      //  query.from(Q_CONTACT).where(Q_CONTACT.id.notIn(list));

     //  List<Long> ids = query.list(Q_ID);

        return rowCount;//contacts;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
