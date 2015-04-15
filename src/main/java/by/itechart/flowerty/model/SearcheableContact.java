package by.itechart.flowerty.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "contact")
public class SearcheableContact {
    private Long id;
    private String name;
    private String surname;

    public SearcheableContact() {
    }

    public SearcheableContact(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;

    }
    @Id
    @Field("ID")
    public Long getId() {
        return id;
    }

    @Field("NAME")
    public String getName() {
        return name;
    }

    @Field("SURNAME")
    public String getSurname() {
        return surname;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
