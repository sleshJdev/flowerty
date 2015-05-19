package by.itechart.flowerty.persistence.mongo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import by.itechart.flowerty.persistence.model.Company;
import by.itechart.flowerty.persistence.mongo.model.FinancialReport;

/**
 * @author Eugene Putsykovich(slesh) May 7, 2015
 *
 */
@Repository
public class FinancialReportRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(FinancialReport report) {
	mongoTemplate.save(report);
    }

    public List<FinancialReport> getALl(Company company) {
	Long companyId = company.getId();
	
	return mongoTemplate.find(Query.query(Criteria.where("companyId").is(companyId)), FinancialReport.class);
    }
}
