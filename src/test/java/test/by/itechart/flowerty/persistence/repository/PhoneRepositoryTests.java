package test.by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Phone;
import by.itechart.flowerty.persistence.repository.PhoneRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.by.itechart.flowerty.config.aware.JpaConfigurationAware;
import test.by.itechart.flowerty.persistence.repository.helper.PhoneRepositoryHelperTests;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Rostislav on 16-May-15
 */

public class PhoneRepositoryTests extends JpaConfigurationAware {

    @Autowired
    private PhoneRepository phoneRepository;

    @Test
    public void findOne_ShouldReturnAPhone() {

        Phone expected = PhoneRepositoryHelperTests.getPhoneWithIdOne();

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
