package by.itechart.flowerty.jms.core;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;

/**
 * @author Eugene Putsykovich(slesh) Apr 30, 2015
 *
 */
public interface Sender {
    public void send(String to, String subject, String text);

    public void send(String to, String subject, String text, Map<String, byte[]> resources)
	    throws MessagingException, IOException;
}
