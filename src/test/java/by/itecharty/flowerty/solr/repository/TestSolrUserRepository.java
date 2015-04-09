package by.itecharty.flowerty.solr.repository;

import by.itechart.flowerty.solr.repository.SolrUserRepository;
import by.itecharty.flowerty.config.JpaConfigurationAware;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Maria
 *         Date: 06.04.15
 */
@Ignore
public class TestSolrUserRepository extends JpaConfigurationAware {
    @Autowired
    private SolrUserRepository userRepository;

}
