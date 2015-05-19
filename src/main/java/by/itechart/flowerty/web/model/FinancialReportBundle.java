package by.itechart.flowerty.web.model;

/**
 * @author Eugene Putsykovich(slesh) May 19, 2015
 *
 */
public class FinancialReportBundle {
    private Integer quantityOrder;
    private Double totalCost;
    
    public FinancialReportBundle() {
    }

    public FinancialReportBundle(Integer quantityOrder, Double totalCost) {
	this.quantityOrder = quantityOrder;
	this.totalCost = totalCost;
    }

    public Integer getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(Integer quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}
