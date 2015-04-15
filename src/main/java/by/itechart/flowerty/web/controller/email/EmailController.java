/**
 * @author Eugene Putsykovich(slesh) Apr 1	4, 2015
 *
 */
package by.itechart.flowerty.web.controller.email;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

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

/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 *         execute send mail for specific contacts
 */
@Controller
public class EmailController {
    private Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

    private static final String TARGET_DIRECTORY = "/home/slesh/temp/";

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

	    processAttachments(attachments);

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

    private static final void processAttachments(MultipartFile[] attachments) throws IOException {
	if(attachments == null){
	    throw new IllegalArgumentException("attachments is null");
	}
	for (MultipartFile attachment : attachments) {
	    String name = String.format("%s_%s", UUID.randomUUID().toString(), attachment.getOriginalFilename());
	    File file = new File(TARGET_DIRECTORY.concat(name));
	    OutputStream outputStream = new FileOutputStream(file);
	    BufferedOutputStream buffereOutputStream = new BufferedOutputStream(outputStream);
	    byte[] bytes = attachment.getBytes();
	    buffereOutputStream.write(bytes);
	    buffereOutputStream.close();

	}
    }
}
