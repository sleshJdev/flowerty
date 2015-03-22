package by.itechart.flowerty.dao.impl;

import by.itechart.flowerty.dao.AbstractDao;
import by.itechart.flowerty.dao.UserDao;
import by.itechart.flowerty.model.User;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 20.03.15
 * Time: 7:25
 * To change this template use File | Settings | File Templates.
 */
public class UserDaoImpl extends AbstractDao implements UserDao{

    @Override
    public void saveUser(User user) {
        persist(user);
    }

    @Override
    public boolean checkUser(String login, String password) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("login", login));
        criteria.add(Restrictions.eq("password", password));
        return criteria.uniqueResult() != null;
    }

    @Override
    public User getUser(Long id) {
        return (User) getSession().load(User.class, id);
    }

    @Override
    public void deleteUserById(Integer id) {
        Query query = getSession().createSQLQuery("delete from User where id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }
}
