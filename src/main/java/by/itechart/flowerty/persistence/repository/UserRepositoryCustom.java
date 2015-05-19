package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Company;
import by.itechart.flowerty.persistence.model.Role;
import by.itechart.flowerty.persistence.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by Rostislav on 18-May-15
 */

@NoRepositoryBean
public interface UserRepositoryCustom {
    public int deleteIdIsIn(List<Long> list);
    
    public Page<User> findByCompany(Company company, Pageable pageable);

    public List<User> findByRoleAndCompany(Role role, Company company);
}
