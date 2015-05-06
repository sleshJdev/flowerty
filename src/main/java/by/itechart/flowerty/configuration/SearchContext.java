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
 * @author Maria
 *         Date:  06.04.15
 */
@Configuration
@ComponentScan
@PropertySource("classpath:solr.properties")
@EnableSolrRepositories(basePackages = { "by.itechart.flowerty" }, multicoreSupport = true)
public class SearchContext {
    @Bean
    public SolrServer solrServer() {
	return new HttpSolrServer("http://localhost:8983/solr");
    }

    @Bean(name = "solrTemplateContact")
    public SolrTemplate solrTemplateContact(SolrServer server) throws Exception {
	SolrTemplate solrTemplateContact = new SolrTemplate(new HttpSolrServer("http://localhost:8983/solr/flowerty-contact"));
	return solrTemplateContact;
    }

    @Bean(name = "solrTemplatePurchase")
    public SolrTemplate solrTemplatePurchase(SolrServer server) throws Exception {
        SolrTemplate solrTemplatePurchase = new SolrTemplate(new HttpSolrServer("http://localhost:8983/solr/flowerty-purchase"));
        return solrTemplatePurchase;
    }
}
