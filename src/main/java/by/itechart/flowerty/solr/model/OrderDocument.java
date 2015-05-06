package by.itechart.flowerty.solr.model;

import by.itechart.flowerty.persistence.model.Company;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.Date;

/**
 * @author Maria
 *         Date: 02.05.15
 */
@SolrDocument(solrCoreName="flowerty-purchase")
public class OrderDocument {
    private String id;
    private String customer;
    private String receiver;
    private Date deliveryDate;
    private Long company;

    private Date deliveryDateBefore;
    private Date deliveryDateAfter;
    public String getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public String getReceiver() {
        return receiver;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public Date getDeliveryDateBefore() {
        return deliveryDateBefore;
    }

    public Date getDeliveryDateAfter() {
        return deliveryDateAfter;
    }

    public Long getCompany() {
        return company;
    }

    @Id
    @Field
    public void setId(String id) {
        this.id = id;
    }

    @Field
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Field
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Field
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Field
    public void setCompany(Long company) {
        this.company = company;
    }

    public void setDeliveryDateBefore(Date deliveryDateBefore) {
        this.deliveryDateBefore = deliveryDateBefore;
    }

    public void setDeliveryDateAfter(Date deliveryDateAfter) {
        this.deliveryDateAfter = deliveryDateAfter;
    }

    public OrderDocument(String id, String consumer, String receiver, Date deliveryDate, Company company) {
        this.id = id;
        this.customer = consumer;
        this.receiver = receiver;
        this.deliveryDate = deliveryDate;
        this.company = company.getId();
    }
    public OrderDocument() {

    }
}
