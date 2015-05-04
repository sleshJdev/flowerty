package by.itechart.flowerty.jms.schedule;

import java.io.IOException;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import by.itechart.flowerty.jms.core.FlowertyMessagePublisher;
import by.itechart.flowerty.local.settings.Settings;
import by.itechart.flowerty.persistence.model.Contact;
import by.itechart.flowerty.persistence.repository.ContactRepository;

/**
 * @author Eugene Putsykovich(slesh) May 4, 2015
 *
 *	notify about birthday with fixed rate
 */
@Component
public class BirthdayNotifyScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(BirthdayNotifyScheduler.class);
    
    @Autowired
    private ContactRepository contactRepository;
    
    @Autowired
    private FlowertyMessagePublisher publisher;
    
    @Autowired
    private Settings settings;
    
    private StringTemplate stringTemplate;
    
    public BirthdayNotifyScheduler() throws IOException {
	String birthdayTemplate = IOUtils.toString(getClass().getResourceAsStream(settings.getBirthdayTemplatePath()));
	
	stringTemplate = new StringTemplate(birthdayTemplate);
	stringTemplate.setAttribute("US_FULL_NAME", settings.getUsFullName());
    }
    
    @Async
    @Scheduled(fixedRate=1 * 60 * 1_000)//1 minute
    public void start(){
	List<Contact> contacts = contactRepository.findByBirthday(new DateTime().toDate());
	if(contacts != null){
	    for(Contact contact : contacts){
		stringTemplate.setAttribute("NAME", contact.getName());
		publisher.send(contact.getEmail(), "Birthday congratulation!", stringTemplate.toString());
		
		LOGGER.info("birthday notify {}", contact.getName());
	    }
	}
    }
}
