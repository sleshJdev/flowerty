package by.itechart.flowerty.web.controller.email;

/**
 * @author Eugene Putsykovich(slesh) Apr 22, 2015
 *
 *         contains template of email
 */
public class FlowertTemplate {
    private String name;
    private String value;

    public FlowertTemplate() {
    }

    public FlowertTemplate(String name, String value) {
	super();
	this.name = name;
	this.value = value;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }
}
