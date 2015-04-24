/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 */
package by.itechart.flowerty.web.controller.email;

import by.itechart.flowerty.model.Contact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class EmailInfo {
    private Contact[] to;
    private String subject;
    private String text;

    public EmailInfo(Contact[] to, String subject, String text) {
	this.to = to;
	this.subject = subject;
	this.text = text;
    }

    public EmailInfo() {
    }

    public Contact[] getTo() {
	return to;
    }

    public void setTo(Contact[] to) {
	this.to = to;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public String getText() {
	return text;
    }

    public void setText(String message) {
	this.text = message;
    }

    @Override
    public String toString() {
	return new StringBuffer("{to:").append(to).append(", subject: ").append(subject).append(", text: ")
		.append(text).append("}").toString();
    }
}
