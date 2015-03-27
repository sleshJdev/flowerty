package by.itechart.flowerty.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import by.itechart.flowerty.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.login = ?1 AND u.password = ?2")
	public User existsByLoginAndPassword(String login, String password);
}
