package by.itechart.flowerty.datamanage;

import by.itechart.flowerty.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 20.03.15
 * Time: 8:26
 * To change this template use File | Settings | File Templates.
 */
public interface UserManager {
    public void saveUser(User user);
    public boolean checkUser (String login, String password);
}
