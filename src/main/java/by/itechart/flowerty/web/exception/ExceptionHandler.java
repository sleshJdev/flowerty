package by.itechart.flowerty.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Eugene Putsykovich(slesh) Mar 24, 2015
 *
 *         General error handler for the application.
 */
@ControllerAdvice
class ExceptionHandler {

    /**
     * Handle exceptions thrown by handlers.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "bad parameters passed")
    @ResponseBody
    public NotFoundException handleException(Exception exception, WebRequest request) {

	return new NotFoundException(exception);
    }
}