package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.User;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 06.04.15
 * Time: 10:21
 * To change this template use File | Settings | File Templates.
 */
public interface SolrUserRepository extends SolrCrudRepository<User, String> {
    public List<User> findByLoginContains(String login);

}
