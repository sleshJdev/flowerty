package test.by.itechart.flowerty.persistence.repository;


import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.by.itechart.flowerty.config.aware.JpaConfigurationAware;
import by.itechart.flowerty.persistence.model.Address;
import by.itechart.flowerty.persistence.repository.AddressRepository;


/**
 @author Мария 28.03.15

 */
@Ignore
public class AddressRepositoryTests extends JpaConfigurationAware {
    private static Validator validator;
    @Autowired
    private AddressRepository addressRepository;
    @Ignore
    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    public void saveAddress_CorrectAddress_SameAddressReturned() {
        Address address = new Address();
//        address.setCountry("Belarus");
//        address.setFlat("10");
//        address.setHouse("12");
//        address.setStreet("Independence");
//        address.setTown("Brest");
        address = addressRepository.save(address);
        Assert.assertNotNull(address);
        Assert.assertNotNull(address.getId());
    }
    @Test
    public void saveAddress_IncorrectAddressTooLongData_ThrowsException() {
        Address address = new Address();
        address.setCountry("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        address.setFlat("10");
        address.setHouse("12");
        address.setStreet("Independence prospect");
        address.setTown("Brest");
       try { Set<ConstraintViolation<Address>> constraintViolations =
                validator.validate( address );
       } catch (ExceptionInInitializerError ex) {
           return;
       }
        Assert.assertEquals(1,2);

    }
}
