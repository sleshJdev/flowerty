package by.itechart.flowerty.persistence.mongo.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Eugene Putsykovich(slesh) May 7, 2015
 *
 */
@Document(collection = FinancialReport.COLLECTION_NAME)
public class FinancialReport implements Serializable {
    private static final long serialVersionUID = -7080179771773584564L;

    protected static final String COLLECTION_NAME = "financial_report";

    @Id
    private String id;

    private Long companyId;
    private Long orderId;
    private Date date;
    private Double totalCost;

    public FinancialReport() {
    }

    public FinancialReport(Long companyId, Long orderId, Date date, Double totalCost) {
	super();
	this.companyId = companyId;
	this.orderId = orderId;
	this.date = date;
	this.totalCost = totalCost;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public Long getCompanyId() {
	return companyId;
    }

    public void setCompanyId(Long companyId) {
	this.companyId = companyId;
    }

    public Long getOrderId() {
	return orderId;
    }

    public void setOrderId(Long orderId) {
	this.orderId = orderId;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public Double getTotalCost() {
	return totalCost;
    }

    public void setTotalCost(Double totalCost) {
	this.totalCost = totalCost;
    }
}
