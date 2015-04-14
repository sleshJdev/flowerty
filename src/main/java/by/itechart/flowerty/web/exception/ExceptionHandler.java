package by.itechart.flowerty.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

 
/**
 * @author Eugene Putsykovich(slesh) Mar 24, 2015
 *
 *         General error handler for the application.
 */
@Controller
public class ExceptionHandler {
    private Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * Handle exceptions thrown by handlers.
     */
//    @org.springframework.web.bind.annotation.ExceptionHandler
//    public ResponseEntity<String> errorHandler(final Exception exc) { 
//	LOGGER.error(exc.getMessage(), exc);
//	return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
//    }
}