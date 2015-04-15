package by.itechart.flowerty.model;

/**
 * @author Maria
 *         Date: 11.04.15
 */

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

//@SolrDocument
public class ContactDocument {
    public static Builder getBuilder(Long id, String title) {
        return new Builder(id, title);
    }

    private String id;

    private String name;

    private String surname;

    public String getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }
    @Id
    @Field("id")
    public void setId(String id) {
        this.id = id;
    }

    @Field("name")
    public void setName(String name) {
        this.name = name;
    }
    @Field("surname")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public static class Builder {
        private ContactDocument build;

        public Builder(Long id, String name) {
            build = new ContactDocument();
            build.id = id.toString();
            build.name = name;
        }

        public Builder surname(String surname) {
            build.surname = surname;
            return this;
        }

        public ContactDocument build() {
            return build;
        }
    }

    public ContactDocument(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public ContactDocument() {
    }
}
