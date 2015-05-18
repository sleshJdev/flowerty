package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.QUser;
import by.itechart.flowerty.persistence.model.User;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
