package by.itechart.flowerty.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import by.itechart.flowerty.web.model.FinancialReportBundle;

/**
 * @author Eugene Putsykovich(slesh) May 19, 2015
 *
 *	
 */
@Controller
public class ReportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);
    
    @ResponseBody
    @RequestMapping(value = "reporst/finances")
    public FinancialReportBundle report(){
	
	LOGGER.info("get financial report");

	
	return null;
    }
}
