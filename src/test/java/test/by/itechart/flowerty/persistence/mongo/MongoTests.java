package test.by.itechart.flowerty.persistence.mongo;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.by.itechart.flowerty.config.aware.JpaConfigurationAware;
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
	Long orderId = 1L;
	Date now = new DateTime().toDate();
	Double cost = 99.9;
	
	FinancialReport financialReport = new FinancialReport(orderId, now, cost);

	repository.save(financialReport);
    }
}
