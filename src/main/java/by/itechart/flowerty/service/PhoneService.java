package by.itechart.flowerty.service;

import java.util.List;

import by.itechart.flowerty.model.Phone;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 21.03.15
 * Time: 20:22
 * To change this template use File | Settings | File Templates.
 */
public interface PhoneService {
    public void savePhone (Phone phone);
    public Phone getPhone(Long id);
    public void deletePhoneById (Integer id);
    public List<Phone> getPhones(Integer contactId);

}
