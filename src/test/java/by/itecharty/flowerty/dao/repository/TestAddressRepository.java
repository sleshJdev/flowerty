package by.itecharty.flowerty.dao.repository;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import by.itechart.flowerty.dao.repository.AddressRepository;
import by.itechart.flowerty.model.Address;
import by.itecharty.flowerty.config.JpaConfigurationAware;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 28.03.15
 * Time: 19:44
 * To change this template use File | Settings | File Templates.
 */
@Ignore
public class TestAddressRepository extends JpaConfigurationAware {
    @Autowired
    private AddressRepository addressRepository;
    @Test
    public void saveAddress() {
        Address address = new Address();
        address.setCountry("Belarus");
        address.setFlat("10");
        address.setHouse("12");
        address.setStreet("Independence prospect");
        address.setTown("Brest");
        address = addressRepository.save(address);
        Assert.assertNotNull(address);
        Assert.assertNotNull(address.getId());
    }
}
