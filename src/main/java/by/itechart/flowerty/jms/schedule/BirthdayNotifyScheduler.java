package by.itechart.flowerty.jms.schedule;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

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
    
    @Autowired(required = true)
    private Settings settings;
    
    private StringTemplate stringTemplate;
    
    @PostConstruct
    public void init() {
	String birthdayTemplate;
	try {
	    birthdayTemplate = IOUtils.toString(getClass().getClassLoader().getResourceAsStream(settings.getBirthdayTemplatePath()));
	} catch (IOException e) {
	    LOGGER.info("birthday template file not found: {}. will be use default template.", e.getMessage());
	    
	    birthdayTemplate = getDefaulTemplate();
	}
	
	stringTemplate = new StringTemplate(birthdayTemplate);
	stringTemplate.setAttribute("US_FULL_NAME", settings.getUsFullName());
    }
    
    @Async
    @Scheduled(fixedRate=5 * 60 * 1_000)//5 minute
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
    
    private static final String getDefaulTemplate(){
	return new StringBuilder()
	    .append("Dear $NAME$,\n")
	    .append("Congratulations!")
	    .append("All of us wish you a very happy birthday and another year filled with  joy, wonder, all good things, success.\n")
	    .append("$US_FULL_NAME$")
	    .toString();
    }
}
