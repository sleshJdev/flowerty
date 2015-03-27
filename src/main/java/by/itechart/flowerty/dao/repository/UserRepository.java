package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
//	@Query("SELECT u FROM User u WHERE u.login = ?1 AND u.password = ?2")
	public User findUserByLoginAndPassword(String login, String password);
    public User findOne(Long id);
    Page<User> findAll(Pageable pageable);
}
