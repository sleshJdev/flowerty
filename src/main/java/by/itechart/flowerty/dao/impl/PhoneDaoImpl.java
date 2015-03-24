package by.itechart.flowerty.dao.impl;

import by.itechart.flowerty.dao.AbstractDao;
import by.itechart.flowerty.dao.PhoneDao;
import by.itechart.flowerty.model.Phone;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 22.03.15
 * Time: 19:19
 * To change this template use File | Settings | File Templates.
 */
@Repository("phoneDao")
public class PhoneDaoImpl extends AbstractDao implements PhoneDao {
    @Override
    public void savePhone(Phone phone) {
        persist(phone);
    }

    @Override
    public Phone getPhone(Long id) {
        return (Phone) getSession().load(Phone.class, id);
    }

    @Override
    public void deletePhoneById(Integer id) {
        Query query = getSession().createSQLQuery("delete from Phone where id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Phone> getPhones(Integer contactId) {
        Query query = getSession().createSQLQuery("from Phone where CONTACT_ID = :id");
        query.setParameter("id", contactId);
        return query.list();
    }
}
