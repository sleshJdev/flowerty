package by.itechart.flowerty.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import by.itechart.flowerty.dao.repository.ContactRepository;
import by.itechart.flowerty.dao.repository.RoleRepository;
import by.itechart.flowerty.dao.repository.UserRepository;
import by.itechart.flowerty.model.Contact;
import by.itechart.flowerty.model.Role;
import by.itechart.flowerty.model.User;
import by.itechart.flowerty.web.model.UserEditBundle;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         Service for user repository
 */
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired RoleRepository roleRepository;

	public User findOne(Long id) {
		return userRepository.findOne(id);
	}
	
	public UserEditBundle getUserEditBundleFor(Long id){
		UserEditBundle bundle = new UserEditBundle();
		bundle.setUser(userRepository.findOne(id));
		bundle.setContacts((List<Contact>) contactRepository.findAll());
		bundle.setRoles((List<Role>)roleRepository.findAll());
	
		return bundle;
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User save(User newUser) {
		return userRepository.save(newUser);
	}

	public Page<User> getPage(int page, int size) {
		return userRepository.findAll(new PageRequest(page, size));
	}

	public User findUserByLoginAndPassword(String username, String password) {
		return userRepository.findUserByLoginAndPassword(username, password);
	}

	public void delete(Long id) {
		userRepository.delete(id);
	}
}
