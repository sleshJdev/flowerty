package by.itechart.flowerty.service;

import by.itechart.flowerty.model.User;

public interface UserService {
    public void saveUser(User user);

    public boolean checkUser(String login, String password);

    public User getUser(Long id);
}
