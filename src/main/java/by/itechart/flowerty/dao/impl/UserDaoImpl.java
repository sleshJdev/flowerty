package by.itechart.flowerty.dao.impl;

import org.springframework.stereotype.Repository;

import by.itechart.flowerty.dao.AbstractDao;
import by.itechart.flowerty.dao.UserDao;
import by.itechart.flowerty.model.User;

@Repository
public class UserDaoImpl extends AbstractDao implements UserDao {

    @Override
    public void saveUser(User user) {
	persist(user);
    }

    @Override
    public boolean checkUser(String login, String password) {
	Object user = getSession()
		.createQuery("from User where login = :login AND password = :password")
		.setString("login", login)
		.setString("password", password)
		.uniqueResult();

	return user == null;
    }

    @Override
    public User getUser(Long id) {
	return (User) getSession().get(User.class, id);
    }

}
