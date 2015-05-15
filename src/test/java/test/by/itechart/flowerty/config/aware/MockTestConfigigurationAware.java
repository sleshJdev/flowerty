package test.by.itechart.flowerty.config.aware;

import java.lang.reflect.Method;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import by.itechart.flowerty.web.exception.ExceptionHandler;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 * 
 *         configuration for testing controllers
 */
public abstract class MockTestConfigigurationAware {
	protected ExceptionHandlerExceptionResolver withExceptionControllerAdvice() {
		final ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
			@Override
			protected ServletInvocableHandlerMethod getExceptionHandlerMethod(final HandlerMethod handlerMethod,
					final Exception exception) {
				Method method = new ExceptionHandlerMethodResolver(ExceptionHandler.class).resolveMethod(exception);
				if (method != null) {
					return new ServletInvocableHandlerMethod(new ExceptionHandler(), method);
				}
				return super.getExceptionHandlerMethod(handlerMethod, exception);
			}
		};
		exceptionResolver.afterPropertiesSet();

		return exceptionResolver;
	}
}
