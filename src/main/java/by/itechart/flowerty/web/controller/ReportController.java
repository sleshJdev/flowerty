package by.itechart.flowerty.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import by.itechart.flowerty.persistence.mongo.model.FinancialReport;
import by.itechart.flowerty.web.model.FinancialReportBundle;
import by.itechart.flowerty.web.service.ReportService;

/**
 * @author Eugene Putsykovich(slesh) May 19, 2015
 *
 *	
 */
@Controller
public class ReportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);
    
    @Autowired
    private ReportService reportService;
    
    @ResponseBody
    @RequestMapping(value = "reporst/finances")
    public FinancialReportBundle report(){
	List<FinancialReport> reports = reportService.getAll();
	FinancialReportBundle bundle = new FinancialReportBundle(0, 0.0);
	if(reports != null){
	    bundle.setQuantityOrder(reports.size());
	    for (FinancialReport report : reports) {
		bundle.setTotalCost(bundle.getTotalCost() + report.getTotalCost());
	    }
	}
	
	LOGGER.info("get financial {} reports with cost: {}", bundle.getQuantityOrder(), bundle.getTotalCost());
	
	return bundle;
    }
}
