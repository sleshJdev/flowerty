package by.itechart.flowerty.datamanage.imp;

import by.itechart.flowerty.dao.UserDao;
import by.itechart.flowerty.datamanage.UserManager;
import by.itechart.flowerty.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 20.03.15
 * Time: 8:27
 * To change this template use File | Settings | File Templates.
 */
public class UserManagerImpl implements UserManager {
    @Autowired
    private UserDao userDao;
    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public boolean checkUser(String login, String password) {
        return userDao.checkUser(login, password);
    }

}
