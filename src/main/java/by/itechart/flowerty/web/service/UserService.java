package by.itechart.flowerty.web.service;

import by.itechart.flowerty.persistence.model.Company;
import by.itechart.flowerty.persistence.model.Contact;
import by.itechart.flowerty.persistence.model.Role;
import by.itechart.flowerty.persistence.model.User;
import by.itechart.flowerty.persistence.repository.ContactRepository;
import by.itechart.flowerty.persistence.repository.RoleRepository;
import by.itechart.flowerty.persistence.repository.UserRepository;
import by.itechart.flowerty.solr.repository.ContactDocumentRepository;
import by.itechart.flowerty.web.model.UserEditBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         service for user repository
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactDocumentRepository contactDocumentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findOne(Long id) {
	return userRepository.findOne(id);
    }

    public UserEditBundle getUserEditBundleFor(Long id) {
	UserEditBundle bundle = new UserEditBundle();

	User user = userRepository.findOne(id);
	user.setPassword(null);
	bundle.setUser(user);
	bundle.setContacts((List<Contact>) contactRepository.findAll());
	bundle.setRoles((List<Role>) roleRepository.findAll());

	return bundle;
    }

    public User getUserByLogin(String login) {
	User user = userRepository.findUserByLogin(login);

	user.setPassword(null);

	return user;
    }

    // public Page<User> findAll() {
    // return userRepository.findAll();
    // }

    @Transactional
    public User save(User newUser) {
	// remove contact from solr context, because he has user and we can't remove it
	contactDocumentRepository.delete(newUser.getContact().getContactDocument());
	
	String password = newUser.getPassword();
	newUser.setPassword(passwordEncoder.encode(password));

	return userRepository.save(newUser);
    }

    @Transactional
    public User update(User newUser) {
	User oldUser = userRepository.findOne(newUser.getId());

	newUser.setPassword(oldUser.getPassword());

	return userRepository.save(newUser);
    }

    public Page<User> getPage(int page, int size) {
	return userRepository.findAll(new PageRequest(page, size));
    }

    public User findUserByLoginAndPassword(String username, String password) {
	return userRepository.findUserByLoginAndPassword(username, password);
    }

    public int deleteIdIn(List<Long> list) {
        return userRepository.deleteIdIsIn(list);
    }

    public List<Role> getRoles() {
	return roleRepository.findAll();
    }

    public Company getCompanyFor(String login) {
	User currentUser = userRepository.findUserByLogin(login);
	return currentUser == null ? null : currentUser.getContact() == null ? null : currentUser.getContact()
		.getCompany();
    }

    public List<User> getUsersByRoleName(String roleString) {
	Role.ROLE_TYPE roleType = Role.ROLE_TYPE.valueOf(roleString);
	if (roleType == null) {
	    return null;
	}
	Role role = roleRepository.findByName(roleType);
	return userRepository.findByRole(role);
    }
}
