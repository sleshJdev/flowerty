package by.itechart.flowerty.local.listener;

import by.itechart.flowerty.persistence.repository.RoleRepository;
import by.itechart.flowerty.local.database.LocalDatabase;
import by.itechart.flowerty.persistence.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

import java.util.List;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015 
 *
 */
public class StartContextApplicationListener implements ApplicationListener<ContextStartedEvent> {
	private Logger LOGGER = LoggerFactory.getLogger(StartContextApplicationListener.class);
	
	/* 
	 * Listen start of context application to initialize local database 
	 */
	@Override
	public void onApplicationEvent(ContextStartedEvent e) {
		LOGGER.info("context application initilizing...");

		ApplicationContext context = e.getApplicationContext();
		RoleRepository roleRepository = context.getBean(RoleRepository.class);
		LocalDatabase.roles = (List<Role>) roleRepository.findAll();
		
		LOGGER.info("done.");
	}
}
