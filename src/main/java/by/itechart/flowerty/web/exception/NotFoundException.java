package by.itechart.flowerty.web.exception;

/**
 * @author Eugene Putsykovich(slesh) Mar 24, 2015
 *
 *         Present NotFoundException class.
 */
public class NotFoundException extends Exception {
    private static final long serialVersionUID = -5714470639035259083L;

    public NotFoundException() {
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotFoundException(String message, Throwable cause) {
	super(message, cause);
    }

    public NotFoundException(String message) {
	super(message);
    }

    public NotFoundException(Throwable cause) {
	super(cause);
    }
}
