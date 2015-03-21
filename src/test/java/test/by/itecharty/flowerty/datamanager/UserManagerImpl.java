package test.by.itecharty.flowerty.datamanager;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import by.itechart.flowerty.configuration.Applicationconfiguration;
import by.itechart.flowerty.model.Employee;
import by.itechart.flowerty.service.EmployeeService;

public class UserManagerImpl {
    @Test
    public void test() {
	AbstractApplicationContext context = new AnnotationConfigApplicationContext(Applicationconfiguration.class);

	EmployeeService service = (EmployeeService) context.getBean("employeeService");

	/*
	 * Create Employee1
	 */
	Employee employee1 = new Employee();
	employee1.setName("Han Yenn");
	employee1.setJoiningDate(new LocalDate(2010, 10, 10));
	employee1.setSalary(new BigDecimal(10000));
	employee1.setSsn("ssn000010151");

	/*
	 * Create Employee2
	 */
	Employee employee2 = new Employee();
	employee2.setName("Dan Thomas");
	employee2.setJoiningDate(new LocalDate(2012, 11, 11));
	employee2.setSalary(new BigDecimal(20000));
	employee2.setSsn("ssn000001252");

	/*
	 * Persist both Employees
	 */
	service.saveEmployee(employee1);
	service.saveEmployee(employee2);

	/*
	 * Get all employees list from database
	 */
	List<Employee> employees = service.findAllEmployees();
	for (Employee emp : employees) {
		System.out.println(emp);
	}

	/*
	 * delete an employee
	 */
	service.deleteEmployeeBySsn("ssn00000002");

	/*
	 * update an employee
	 */

	Employee employee = service.findBySsn("ssn00000001");
	employee.setSalary(new BigDecimal(50000));
	service.updateEmployee(employee);

	/*
	 * Get all employees list from database
	 */
	List<Employee> employeeList = service.findAllEmployees();
	for (Employee emp : employeeList) {
		System.out.println(emp);
	}

	context.close();
    }

}
