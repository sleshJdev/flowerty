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
    
    private Long orderId;
    private Date date;
    private Double totalCost;

    public FinancialReport() {
    }

    public FinancialReport(Long orderId, Date date, Double totalCost) {
	this.orderId = orderId;
	this.date = date;
	this.totalCost = totalCost;
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
