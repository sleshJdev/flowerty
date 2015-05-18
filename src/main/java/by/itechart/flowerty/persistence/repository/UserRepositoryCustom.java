package by.itechart.flowerty.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import by.itechart.flowerty.persistence.model.Company;
import by.itechart.flowerty.persistence.model.User;

/**
 * Created by Rostislav on 18-May-15
 */

@NoRepositoryBean
public interface UserRepositoryCustom {
    public int deleteIdIsIn(List<Long> list);
    
    public Page<User> findByCompany(Company company, Pageable pageable);
}
