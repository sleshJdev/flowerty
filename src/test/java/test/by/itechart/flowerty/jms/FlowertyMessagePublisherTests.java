package test.by.itechart.flowerty.jms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import by.itechart.flowerty.configuration.JmsConfiguration;
import by.itechart.flowerty.configuration.LocalConfiguration;
import by.itechart.flowerty.configuration.MailConfiguration;
import by.itechart.flowerty.jms.core.FlowertyMessagePublisher;

/**
 * @author Eugene Putsykovich(slesh) Apr 29, 2015
 *
 *         strange test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { MailConfiguration.class, JmsConfiguration.class, LocalConfiguration.class })
public class FlowertyMessagePublisherTests {
    private final String to = "studentbntu@mail.ru";
    private final String subject = "flowerty-promotion";
    private final String text = "flowerty-text";

    @Autowired
    private FlowertyMessagePublisher messagePublisher;

    @Test
    public void send_PassPlainMessageInfo_ShouldSendIt() {
	messagePublisher.send(to, subject, text);
    }

    @Test
    public void send_PassMessageWithAttachment_ShoudSendIt() throws IOException, MessagingException {
	Map<String, byte[]> attachments = new HashMap<String, byte[]>();
	attachments.put("flowerty.png", createAttachment("flowerty"));
	attachments.put("team.png", createAttachment("team"));
	attachments.put("B.png", createAttachment("B"));

	messagePublisher.send(to, subject, text, attachments);
    }

    private static byte[] createAttachment(String name) throws IOException {
	BufferedImage attachment = new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);
	Graphics graphics = attachment.getGraphics();
	Font font = new Font("Serif", Font.PLAIN, 18);
	graphics.setFont(font);
	graphics.setColor(Color.GRAY);
	graphics.drawString(name, 0, 20);

	ByteArrayOutputStream output = new ByteArrayOutputStream();
	ImageIO.write(attachment, "png", output);
	output.flush();

	byte[] bytes = output.toByteArray();
	output.close();

	return bytes;
    }
}
