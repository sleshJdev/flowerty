package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.repository.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
public interface UserRepository extends PagingAndSortingRepository <User, Long> {
	public User findUserByLoginAndPassword(String login, String password);
    public User findUserByLogin(String login);
    public User findOne(Long id);
//    @Query("select u from User u where u.contact.company = ?1")
//    public Page<User> findByCompany(Company company, Pageable pageable);
    public User save (User user);
   // public Page<User> findAll(Pageable pageable);
    public void delete(Long id);
}
