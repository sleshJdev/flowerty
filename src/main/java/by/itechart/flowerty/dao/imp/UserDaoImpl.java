package by.itechart.flowerty.dao.imp;

import by.itechart.flowerty.dao.UserDao;
import by.itechart.flowerty.domain.User;
import org.hibernate.*;
import by.itechart.flowerty.domain.User;
import org.springframework.beans.factory.parsing.Location;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 20.03.15
 * Time: 7:25
 * To change this template use File | Settings | File Templates.
 */
public class UserDaoImpl implements UserDao{

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private SessionFactory sessionFactory;

    @Override
    public void saveUser(User user) {
       //  this.getSessionFactory().getCurrentSession().saveOrUpdate(user);
        //To change body of implemented methods use File | Settings | File Templates.
        Session session =  this.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(user);
        //List results = session.createQuery("from Event e where e.name='testSave'").list();

       // System.out.println(results.size());

        tx.commit();
        session.close();
        sessionFactory.close();
    }

    @Override
    public boolean checkUser(String login, String password) {
        //To change body of implemented methods use File | Settings | File Templates.
        Session session =  this.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("select 1 from User u where u.login = :login and u.password = :password");
        query.setString("login", login);
        query.setString("password", password);
        boolean ret = query.uniqueResult() != null;
        session.close();
        return ret;
    }

    @Override
    public User getUser(Integer id) {
        if (id == null) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
        }
        Session session =  this.getSessionFactory().getCurrentSession();
        User user = (User)session.load(User.class, id);
        Hibernate.initialize(user);
        return user;
    }
}
