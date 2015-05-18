package by.itechart.flowerty.persistence.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by Rostislav on 18-May-15
 */

@NoRepositoryBean
public interface UserRepositoryCustom {
    public int deleteIdIsIn(List<Long> list);
}
