package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Role;
import by.itechart.flowerty.persistence.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    public User findUserByLoginAndPassword(String login, String password);

    public User findUserByLogin(String login);

    public User findOne(Long id);

    public List<User> findByRole(Role role);
}
