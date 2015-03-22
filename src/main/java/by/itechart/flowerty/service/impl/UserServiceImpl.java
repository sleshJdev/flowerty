package by.itechart.flowerty.service.impl;

import by.itechart.flowerty.dao.UserDao;
import by.itechart.flowerty.model.User;
import by.itechart.flowerty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 21.03.15
 * Time: 18:01
 * To change this template use File | Settings | File Templates.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao dao;
    @Override
    public void saveUser(User user) {
        dao.saveUser(user);
    }

    @Override
    public boolean checkUser(String login, String password) {
        return dao.checkUser(login, password);
    }

    @Override
    public User getUser(Long id) {
        return dao.getUser(id);
    }

    @Override
    public void deleteUserById(Integer id) {
        dao.deleteUserById(id);
    }


}
