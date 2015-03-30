package by.itechart.flowerty.web.model;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 */
public class SigninForm {
	private String username;
	private String password;

	public SigninForm() {
	}

	public SigninForm(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
