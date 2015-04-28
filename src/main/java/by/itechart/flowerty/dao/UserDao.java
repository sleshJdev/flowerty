package by.itechart.flowerty.dao;

import by.itechart.flowerty.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 20.03.15
 * Time: 7:25
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface UserDao {
    public void saveUser (User user);
    public boolean checkUser (String login, String password);
    public User getUser(Long id);
    public void deleteUserById(Integer id);
}
