/**
 * @author Eugene Putsykovich(slesh) Apr 1	4, 2015
 *
 */
package by.itechart.flowerty.web.controller.email;

import java.io.IOException;

import javax.mail.MessagingException;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import by.itechart.flowerty.local.settings.Settings;
import by.itechart.flowerty.web.controller.util.FlowertUtil;

/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 *         execute send mail for specific contacts
 */
@Controller
public class EmailController {
    private Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private Settings settings;
    
    @Autowired
    private MailService mailService;

    @ResponseBody
    @RequestMapping(value = "email/send", method = RequestMethod.POST)
    public void sendEmail(@RequestParam("email") String emailJson,
	    @RequestPart(value = "file", required = false) MultipartFile[] attachments) throws IOException {
	LOGGER.info("send email: {}. number of attachments: {}", emailJson, attachments == null ? 0
		: attachments.length);

	EmailInfo emailInfo = null;
	try {
	    ObjectMapper objectMapper = new ObjectMapper();
	    emailInfo = objectMapper.readValue(emailJson, EmailInfo.class);

	    FlowertUtil.processMultiparts(settings.getAttachmentsPath(), attachments);

	    if (attachments == null || attachments.length == 0) {
		mailService.send(emailInfo);
	    } else {
		mailService.send(emailInfo, attachments);
	    }
	} catch (IOException | MessagingException e) {
	    LOGGER.error(e.getMessage());
	    throw new IOException(e);
	}
    }
}
