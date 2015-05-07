package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Company;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Eugene Putsykovich(slesh) May 5, 2015
 *
 */
public interface CompanyRepository extends CrudRepository<Company, Long>{
}
