package test.by.itechart.flowerty.persistence.repository.helper;

import by.itechart.flowerty.persistence.model.Phone;

/**
 * Created by Rostislav on 16-May-15
 */

public abstract class PhoneRepositoryHelperTest {

    public static Phone getPhoneWithIdOne() {
        Phone phone = new Phone();

        phone.setId(1L);
        phone.setComment("Comment1");
        phone.setCountry("12");
        phone.setNumber("56789");
        phone.setOperator("34");
        phone.setContact(ContactRepositoryHelperTest.getContactWithIdOne());
        phone.setType(Phone.PHONE_TYPE.CELL);

        return phone;
    }
}
