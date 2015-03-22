package by.itechart.flowerty.dao;

import by.itechart.flowerty.model.Phone;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 22.03.15
 * Time: 19:18
 * To change this template use File | Settings | File Templates.
 */
public interface PhoneDao {
    public void savePhone (Phone phone);
    public Phone getPhone(Long id);
    public void deletePhoneById (Integer id);
    public List<Phone> getPhones(Integer contactId);
}
