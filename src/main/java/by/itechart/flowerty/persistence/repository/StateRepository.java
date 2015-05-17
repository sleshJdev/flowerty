package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.State;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Катерина on 26.04.2015.
 */
public interface StateRepository extends PagingAndSortingRepository<State, Long> {
    public List<State> findByDescription(State.DESCRIPTION_TYPE description);
}
