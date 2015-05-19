package by.itechart.flowerty.persistence.repository.util;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * @author Eugene Putsykovich(slesh) May 19, 2015
 *
 */
public class PageUtil<T> {
    public final Page<T> preparePage(List<T> orders, Pageable pageable){
	int listSize = orders.size();
	int pageSize = pageable.getPageSize();
	int start = pageable.getPageNumber();
	
	pageSize = pageSize > listSize ? listSize : pageSize;
	start = start * pageSize > listSize ? 0 : start;
	int end = (start + 1) * pageSize;
	
	orders = orders.subList(start * pageSize, end >= listSize ? listSize : end);

	return new PageImpl<T>(orders, pageable, listSize);
    }
}
