/**
 * @author Eugene Putsykovich(slesh) Apr 1	4, 2015
 *
 */
package by.itechart.flowerty.web.controller.email;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;

import org.antlr.stringtemplate.StringTemplate;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import by.itechart.flowerty.local.settings.Settings;
import by.itechart.flowerty.persistence.repository.model.Contact;
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
    @RequestMapping(value = "email/templates", method = RequestMethod.GET)
    public FlowertTemplate[] emailTemplate() throws JsonGenerationException, JsonMappingException,
	    FileNotFoundException, IOException {
	return getTemplates();
    }

    @ResponseBody
    @RequestMapping(value = "email/send", method = RequestMethod.POST)
    public void sendEmail(
	    @RequestParam("email") String emailJson, 
	    @RequestParam("template") String templateJson,
	    @RequestPart(value = "file", required = false) MultipartFile[] attachments) throws IOException {
	LOGGER.info("send email: {}. template: {}, number of attachments: {}", emailJson, templateJson,
		attachments == null ? 0 : attachments.length);

	try {
	    ObjectMapper objectMapper = new ObjectMapper();
	    EmailInfo emailInfo = objectMapper.readValue(emailJson, EmailInfo.class);
	    
	    LOGGER.info("create email info object from json success! details: contact quantity: {}",
		    emailInfo.getTo().length);

	    LOGGER.info("create flowerty template object from json success!");
	    FlowertUtil.saveMultiparts(settings.getAttachmentsPath(), attachments);

	    LOGGER.info("save attachments success!");

	    FlowertTemplate template = objectMapper.readValue(templateJson, FlowertTemplate.class);
	    for (Contact to : emailInfo.getTo()) {
		emailInfo.setText(getText(template, to.getName()));
		if (attachments == null || attachments.length == 0) {
		    mailService.send(to.getEmail(), emailInfo.getSubject(), emailInfo.getText());
		} else {
		    mailService.send(to.getEmail(), emailInfo.getSubject(), emailInfo.getText(), attachments);
		}
	    }
	} catch (IOException | MessagingException e) {
	    LOGGER.error(e.getMessage());
	    throw new IOException(e);
	}
    }

    private FlowertTemplate[] getTemplates() throws FileNotFoundException, IOException {
	File folder = new File(getClass().getClassLoader().getResource("templates").getPath());
	File[] files = folder.listFiles();
	FlowertTemplate[] templates = new FlowertTemplate[files.length];
	for (int i = 0; i < files.length; ++i) {
	    String name = files[i].getName();
	    String value = IOUtils.toString(new FileInputStream(files[i]));
	    templates[i] = new FlowertTemplate(name, value);
	}

	return templates;
    }

    private String getText(FlowertTemplate template, String toName) {
	StringTemplate st = new StringTemplate(template.getValue());
	st.setAttribute("NAME", toName);
	st.setAttribute("US_FULL_NAME", "flowerty-full-name");// TODO:replace to actual data
	st.setAttribute("US_PHONE", "flowerty-phone");// TODO:replace to actual data
	st.setAttribute("US_EMAIL", "flowerty-email");// TODO:replace to actual data

	return st.toString();
    }
}
