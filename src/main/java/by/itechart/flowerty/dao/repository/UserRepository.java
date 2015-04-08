package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.Company;
import by.itechart.flowerty.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findUserByLoginAndPassword(String login, String password);
    public User findUserByLogin(String login);
    public User findOne(Long id);
    @Query("select u from User u where u.contact.company = ?1")
    public Page<User> findByCompany(Company company, Pageable pageable);
    public User save (User user);
    //Page<User> findAll(Pageable pageable);
    public void delete(Long id);
}
