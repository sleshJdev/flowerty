package by.itechart.flowerty.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.itechart.flowerty.dao.UserDao;
import by.itechart.flowerty.model.User;
import by.itechart.flowerty.service.UserService;

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
}
