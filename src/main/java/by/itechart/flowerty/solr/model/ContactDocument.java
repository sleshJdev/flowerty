package by.itechart.flowerty.solr.model;

/**
* @author Maria
*         Date: 11.04.15
*/

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.util.Date;

//@SolrDocument
public class ContactDocument {
    public static Builder getBuilder(Long id, String title) {
        return new Builder(id, title);
    }

    private String id;
    private String name;
    private String surname;
    private Date birthday;
    private BirthdaySearchType searchType;
    private String email;
    private String country;
    private String town;
    private String street;
    private String house;
    private String flat;

    public static enum BirthdaySearchType {
        BEFORE, BY_DATE, AFTER
    }
    public String getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public BirthdaySearchType getSearchType() {
        return searchType;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public String getTown() {
        return town;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getFlat() {
        return flat;
    }

    @Id
    @Field
    public void setId(String id) {
        this.id = id;
    }

    @Field
    public void setName(String name) {
        this.name = name;
    }
    @Field
    public void setSurname(String surname) {
        this.surname = surname;
    }
    @Field
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    @Field
    public void setSearchType(BirthdaySearchType searchType) {
        this.searchType = searchType;
    }
    @Field
    public void setEmail(String email) {
        this.email = email;
    }
    @Field
    public void setCountry(String country) {
        this.country = country;
    }
    @Field
    public void setTown(String town) {
        this.town = town;
    }
    @Field
    public void setStreet(String street) {
        this.street = street;
    }
    @Field
    public void setHouse(String house) {
        this.house = house;
    }
    @Field
    public void setFlat(String flat) {
        this.flat = flat;
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


    public ContactDocument() {
    }
}
