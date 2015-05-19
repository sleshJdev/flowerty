package by.itechart.flowerty.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itechart.flowerty.persistence.model.Company;
import by.itechart.flowerty.persistence.mongo.model.FinancialReport;
import by.itechart.flowerty.persistence.mongo.repository.FinancialReportRepository;
import by.itechart.flowerty.security.service.UserDetailsServiceImpl;

/**
 * @author Eugene Putsykovich(slesh) May 19, 2015
 *
 */
@Service
public class ReportService {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private FinancialReportRepository financialReportRepository;

    public List<FinancialReport> getAll() {
	Company company = userDetailsService.getCurrentContact().getCompany();

	return financialReportRepository.getALl(company);
    }
}
