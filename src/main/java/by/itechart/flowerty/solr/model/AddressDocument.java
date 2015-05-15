package by.itechart.flowerty.solr.model;

import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.Date;

/**
 * @author Maria
 *         Date: 15.05.15
 */
@SolrDocument(solrCoreName="flowerty-contact")
public class AddressDocument {
    private String id;
    private String name;
    private String surname;
    private String fathername;
    private Date birthday;
    private Integer day;
    private Integer month;
    private Integer year;
    private String email;
    private String country;
    private String town;
    private String street;
    private String house;
    private String flat;
    private Long company;
}
