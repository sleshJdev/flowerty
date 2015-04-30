package by.itechart.flowerty.configuration;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

/**
 * User: Мария Date: 06.04.15 Time: 9:57
 */
@Configuration
@ComponentScan
@PropertySource("classpath:solr.properties")
@EnableSolrRepositories(basePackages = { "by.itechart.flowerty" }, multicoreSupport = true)
public class SearchContext {
    @Bean
    public SolrServer solrServer() {
	return new HttpSolrServer("http://localhost:8983/solr/flowerty");
    }

    @Bean
    public SolrTemplate solrTemplate(SolrServer server) throws Exception {
	SolrTemplate solrTemplate = new SolrTemplate(server);
	return solrTemplate;
    }
}
