package by.itechart.flowerty.web.model;

import by.itechart.flowerty.model.Contact;
import by.itechart.flowerty.model.Role;
import by.itechart.flowerty.model.User;

import java.util.List;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 * 
 *         Bundle for editing of user
 */
public class UserEditBundle {
	private User user;
	private List<Contact> contacts;
	private List<Role> roles;

	public UserEditBundle() {
	}
	
	public UserEditBundle(User user, List<Contact> contacts, List<Role> roles) {
		super();
		this.user = user;
		this.contacts = contacts;
		this.roles = roles;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Role> getRoles() {
		return roles;
	}
}
