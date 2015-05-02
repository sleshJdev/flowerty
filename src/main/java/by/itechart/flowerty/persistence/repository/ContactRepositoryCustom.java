package by.itechart.flowerty.persistence.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author Maria Date: 17.04.15
 */
@NoRepositoryBean
public interface ContactRepositoryCustom {
    public int deleteIdNotIn(List<Long> list);
}
