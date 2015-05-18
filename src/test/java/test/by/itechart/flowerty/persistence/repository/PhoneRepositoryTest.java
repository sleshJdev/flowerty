package test.by.itechart.flowerty.persistence.repository;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.by.itechart.flowerty.config.aware.JpaConfigurationAware;
import test.by.itechart.flowerty.persistence.repository.helper.PhoneRepositoryHelperTest;
import by.itechart.flowerty.persistence.model.Phone;
import by.itechart.flowerty.persistence.repository.PhoneRepository;

/**
 * Created by Rostislav on 16-May-15
 */

public class PhoneRepositoryTest extends JpaConfigurationAware {

    @Autowired
    private PhoneRepository phoneRepository;

    @SuppressWarnings("unchecked")
    @Test
    public void findOne_ShouldReturnAPhone() {

        Phone expected = PhoneRepositoryHelperTest.getPhoneWithIdOne();

        Phone actual = phoneRepository.findOne(1L);

        assertNotNull(actual);
        assertThat(actual, allOf(
                hasProperty("id", is(expected.getId())),
                hasProperty("comment", is(expected.getComment())),
                hasProperty("country", is(expected.getCountry())),
                hasProperty("number", is(expected.getNumber())),
                hasProperty("operator", is(expected.getOperator())),
                hasProperty("contact", is(expected.getContact())),
                hasProperty("type", is(expected.getType()))
        ));
    }
}
