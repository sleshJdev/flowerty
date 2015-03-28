package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	public User findUserByLoginAndPassword(String login, String password);
    public User findOne(Long id);
   // public Page<User> findByCompany(Company company, Pageable pageable);
    public User save (User user);
    Page<User> findAll(Pageable pageable);
    public void delete(Long id);
   // @Query("select c.contact.name, c.contact.surname, c.contact.fathername, c.login, c.role from User c where c.contact.company = ?1")
   // List<User> getShortUsers (Company company);
}
