package test.by.itechart.flowerty.persistence.mongo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.by.itechart.flowerty.config.aware.JpaConfigurationAware;
import by.itechart.flowerty.persistence.mongo.model.FinancialReport;
import by.itechart.flowerty.persistence.mongo.repository.FinancialReportRepository;

/**
 * @author Eugene Putsykovich(slesh) May 7, 2015
 *
 */
public class MongoTests extends JpaConfigurationAware {
    @Autowired
    private FinancialReportRepository repository;

    @Test
    public void save_PassValidFinancialReportObject_ShouldSaveIt() {
	FinancialReport financialReport = new FinancialReport(3, 15.7);
	repository.save(financialReport);
    }
}
