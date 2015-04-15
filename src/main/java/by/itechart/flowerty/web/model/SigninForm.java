package by.itechart.flowerty.web.model;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 */
public class SigninForm {
	private String login;
	private String password;

	public SigninForm() {
	}

	public SigninForm(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String username) {
		this.login = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
