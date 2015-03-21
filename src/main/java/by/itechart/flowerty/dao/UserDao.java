package by.itechart.flowerty.dao;

import by.itechart.flowerty.model.User;

public interface UserDao {
    public void saveUser(User user);

    public boolean checkUser(String login, String password);

    public User getUser(Long id);
}
