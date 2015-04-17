//package by.itechart.flowerty.configuration;
//
///**
//* @author Maria
//*         Date: 11.04.15
//*/
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.data.solr.core.SolrTemplate;
//import org.springframework.data.solr.repository.config.EnableSolrRepositories;
//import org.springframework.data.solr.server.support.HttpSolrServerFactoryBean;
//
//import javax.annotation.Resource;
//
//@Configuration
//@EnableSolrRepositories("by.itechart.flowerty.dao.repository")
//@Profile("prod")
//@PropertySource("classpath:solr.properties")
//public class HttpSolrContext {
//
//    @Resource
//    private Environment environment;
//
//    @Bean
//    public HttpSolrServerFactoryBean solrServerFactoryBean() {
//        HttpSolrServerFactoryBean factory = new HttpSolrServerFactoryBean();
//
//        factory.setUrl(environment.getRequiredProperty("solr.server.url"));
//
//        return factory;
//    }
//
//    @Bean
//    public SolrTemplate solrTemplate() throws Exception {
//        return new SolrTemplate(solrServerFactoryBean().getObject());
//    }
//}
