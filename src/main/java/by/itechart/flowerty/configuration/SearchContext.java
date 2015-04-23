package by.itechart.flowerty.configuration;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.convert.SolrConverter;
import org.springframework.data.solr.core.mapping.SolrPersistentEntity;
import org.springframework.data.solr.core.mapping.SolrPersistentProperty;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.server.support.HttpSolrServerFactoryBean;
import javax.annotation.Resource;
import org.springframework.data.solr.server.support.HttpSolrServerFactoryBean;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 06.04.15
 * Time: 9:57
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@ComponentScan
//@Import({ JpaConfiguration.class, SearchContext.class })
@PropertySource("classpath:solr.properties")
@EnableSolrRepositories(basePackages={"by.itechart.flowerty"}, multicoreSupport = true)
public class SearchContext {
//    @Resource
//    private Environment environment;
    @Bean
    public SolrServer solrServer() {
        return new HttpSolrServer("http://localhost:8983/solr/flowerty");
    }
    @Bean
    public SolrTemplate solrTemplate(SolrServer server) throws Exception {
        SolrTemplate solrTemplate = new SolrTemplate(server);
    //    solrTemplate.setSolrCore("flowerty");
        return solrTemplate;
    }
//    @Bean
//    public HttpSolrServerFactoryBean solrServerFactoryBean() {
//        HttpSolrServerFactoryBean factory = new HttpSolrServerFactoryBean();
//
//        factory.setUrl(environment.getRequiredProperty("solr.server.url"));
//
//        return factory;
//    }

//    @Bean
//    public SolrTemplate solrTemplate() throws Exception {
//        return new SolrTemplate(solrServerFactoryBean().getObject());
//    }

}
