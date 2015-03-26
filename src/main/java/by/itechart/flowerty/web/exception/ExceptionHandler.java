package by.itechart.flowerty.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Eugene Putsykovich(slesh) Mar 24, 2015
 *
 *         General error handler for the application.
 */
@ControllerAdvice
public class ExceptionHandler {
    
    /**
     * Handle exceptions thrown by handlers.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "bad parameters passed")
    @ResponseBody
    public NotFoundException handleException(Exception exception, HttpServletRequest request, HttpServletResponse response) {
	response.setStatus(404);
	
	return new NotFoundException(exception);
    }
}