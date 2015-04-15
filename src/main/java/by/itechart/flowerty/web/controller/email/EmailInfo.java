/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 */
package by.itechart.flowerty.web.controller.email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class EmailInfo {
    private String to;
    private String subject;
    private String text;

    public EmailInfo(String to, String subject, String text) {
	this.to = to;
	this.subject = subject;
	this.text = text;
    }

    public EmailInfo() {
    }

    public String getTo() {
	return to;
    }

    public void setTo(String to) {
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
