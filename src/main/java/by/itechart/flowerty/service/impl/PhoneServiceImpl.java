package by.itechart.flowerty.service.impl;

import by.itechart.flowerty.dao.PhoneDao;
import by.itechart.flowerty.model.Phone;
import by.itechart.flowerty.model.User;
import by.itechart.flowerty.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 22.03.15
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
@Service("phoneService")
@Transactional
public class PhoneServiceImpl implements PhoneService{
    @Autowired
    private PhoneDao dao;
    @Override
    @Transactional
    public void savePhone(Phone phone) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Phone getPhone(Long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    @Transactional
    public void deletePhoneById(Integer id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Phone> getPhones(Integer contactId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
