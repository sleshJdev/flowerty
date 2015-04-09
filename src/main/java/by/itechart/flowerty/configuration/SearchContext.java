package by.itechart.flowerty.configuration;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 06.04.15
 * Time: 9:57
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableSolrRepositories(basePackages = { "by.itechart.flowerty" }, multicoreSupport = true)
public class SearchContext {

        @Bean
        public SolrServer solrServer(@Value("${solr.host}") String solrHost) {
            return new HttpSolrServer(solrHost);
        }

}
