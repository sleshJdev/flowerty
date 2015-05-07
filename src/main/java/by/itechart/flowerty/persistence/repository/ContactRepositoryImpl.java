package by.itechart.flowerty.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.*;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Maria
 *         Date: 17.04.15
 */
public class ContactRepositoryImpl implements ContactRepositoryCustom {
   @Autowired
   private EntityManager em;

    @Override
    @Transactional
    public int deleteIdNotIn(List<Long> list) {
      int rowCount = em.createQuery("DELETE FROM Contact c WHERE c.id NOT IN :list").setParameter("list",list).executeUpdate();
      return rowCount;
    }
}
