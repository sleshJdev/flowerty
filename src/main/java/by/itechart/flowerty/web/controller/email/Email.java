/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 */
package by.itechart.flowerty.web.controller.email;

import java.util.Arrays;

/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 *         present pojo class. contains info about email.
 */
public class Email {
    private String[] to;
    private String subject;
    private String template;
    private String text;

    public Email() {
    }

    public Email(String[] to, String subject, String template, String text) {
	super();
	this.to = to;
	this.subject = subject;
	this.template = template;
	this.text = text;
    }

    public String[] getTo() {
	return to;
    }

    public void setTo(String[] to) {
	this.to = to;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public String getTemplate() {
	return template;
    }

    public void setTemplate(String template) {
	this.template = template;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    @Override
    public String toString() {
	return new StringBuffer().append("email:{to:").append(Arrays.toString(to)).append(",subject:").append(subject)
		.append(", template: ").append(template).append(", text: ").append(text).append("}").toString();
    }
}
