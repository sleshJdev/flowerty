package by.itechart.flowerty.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import by.itechart.flowerty.persistence.repository.model.State;
/**
 * Created by Катерина on 26.04.2015.
 */
public interface StateRepository extends PagingAndSortingRepository<State, Long> {
}
