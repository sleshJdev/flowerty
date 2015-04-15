/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 */
package by.itechart.flowerty.web.controller.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 *	execute send mail for specific contacts
 */
@Controller
public class EmailController {
    private Logger LOGGER = LoggerFactory.getLogger(EmailController.class);
    
    @RequestMapping(value = "email/send", method = RequestMethod.POST)
    public String sendEmail(@RequestBody Email email){
	LOGGER.info("send email: {}", email.toString());
    
	return "home/index";
    }
}
