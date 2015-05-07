package by.itechart.flowerty.persistence.mongo.model;

import java.io.Serializable;

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
    private Integer orderQuantity;
    private Double totalCost;

    public FinancialReport() {
    }

    public FinancialReport(Integer orderQuantity, Double totalCost) {
	this.orderQuantity = orderQuantity;
	this.totalCost = totalCost;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public Integer getOrderQuantity() {
	return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
	this.orderQuantity = orderQuantity;
    }

    public Double getTotalCost() {
	return totalCost;
    }

    public void setTotalCost(Double totalCost) {
	this.totalCost = totalCost;
    }
}
