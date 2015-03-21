package by.itechart.flowerty.dao.service;

import by.itechart.flowerty.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 21.03.15
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {
    public void saveUser (User user);
    public boolean checkUser (String login, String password);
    public User getUser(Integer id);
    public void deleteUserById (Integer id);
}
