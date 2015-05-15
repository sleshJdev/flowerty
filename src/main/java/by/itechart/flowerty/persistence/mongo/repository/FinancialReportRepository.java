package by.itechart.flowerty.persistence.mongo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import by.itechart.flowerty.persistence.mongo.model.FinancialReport;

/**
 * @author Eugene Putsykovich(slesh) May 7, 2015
 *
 */
@Repository
public class FinancialReportRepository {
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public void save(FinancialReport report){
	mongoTemplate.save(report);
    }
}
