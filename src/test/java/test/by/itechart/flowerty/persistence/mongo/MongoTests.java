package test.by.itechart.flowerty.persistence.mongo;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.by.itechart.flowerty.config.aware.JpaConfigurationAware;
import by.itechart.flowerty.persistence.model.Company;
import by.itechart.flowerty.persistence.mongo.model.FinancialReport;
import by.itechart.flowerty.persistence.mongo.repository.FinancialReportRepository;

/**
 * @author Eugene Putsykovich(slesh) May 7, 2015
 *
 *	mongo tests
 */
public class MongoTests extends JpaConfigurationAware {
    @Autowired
    private FinancialReportRepository repository;

    @Test
    public void save_PassValidFinancialReportObject_ShouldSaveIt() {
	Long companyId = 2L;
	Long orderId = 1L;
	Date now = new DateTime().toDate();
	Double cost = 99.9;
	
	FinancialReport financialReport = new FinancialReport(companyId, orderId, now, cost);

	repository.save(financialReport);
    }
    
    @Test
    public void getAll_PassValidComapnyId_ShouldReturnCollection(){
	Company company = new Company("website", "company", 1L);
	
	List<FinancialReport> collection = repository.getALl(company);
	
	Assert.assertNotNull(collection);
    }
}
