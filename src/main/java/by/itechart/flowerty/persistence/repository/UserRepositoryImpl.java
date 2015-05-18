package by.itechart.flowerty.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import by.itechart.flowerty.persistence.model.Company;
import by.itechart.flowerty.persistence.model.QUser;
import by.itechart.flowerty.persistence.model.User;

/**
 * Created by Rostislav on 18-May-15
 */

public class UserRepositoryImpl extends QueryDslRepositorySupport implements UserRepositoryCustom {
    private static final QUser USER = QUser.user;

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    @Transactional
    public int deleteIdIsIn(List<Long> list) {
        return (int) delete(USER).where(USER.id.in(list)).execute();
    }
    
    @Override
    public Page<User> findByCompany(Company company, Pageable pageable) {
	List<User> orders = 
		from(USER)
			.where(USER.contact.company.eq(company))
		.list(USER);
	
	return new PageImpl<User>(orders);
    }
}
