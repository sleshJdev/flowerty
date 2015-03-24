package by.itechart.flowerty.dao.repository;

import org.springframework.data.repository.CrudRepository;

import by.itechart.flowerty.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
}
