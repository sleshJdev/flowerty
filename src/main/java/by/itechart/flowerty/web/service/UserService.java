package by.itechart.flowerty.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import by.itechart.flowerty.dao.repository.UserRepository;
import by.itechart.flowerty.model.User;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         Service for user repository
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * @param id
	 * @return user with passed id
	 */
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	/**
	 * @return all users
	 */
	public List<User> findAll() {
		return userRepository.findAll();
	}

	/**
	 * @param newUser
	 * @return saved user
	 */
	public User save(User newUser) {
		return userRepository.save(newUser);
	}

	/**
	 * @param pageRequest
	 * @return page with users
	 */
	public List<User> findAll(PageRequest pageRequest) {
		return userRepository.findAll(pageRequest).getContent();
	}

	/**
	 * @param username
	 * @param password
	 * @return user with passed credentials
	 */
	public User findUserByLoginAndPassword(String username, String password) {
		return userRepository.findUserByLoginAndPassword(username, password);
	}

	/**
	 * @param id of deletable user
	 */
	public void delete(Long id) {
		userRepository.delete(id);
	}
}
