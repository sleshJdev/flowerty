package by.itechart.flowerty.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import by.itechart.flowerty.model.Employee;

@Transactional
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
