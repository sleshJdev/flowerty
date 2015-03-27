package by.itecharty.flowerty.config;

import java.lang.reflect.Method;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import by.itechart.flowerty.web.exception.ExceptionHandler;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 * 
 *         Configuration for testing Controllers
 */
@RunWith(MockitoJUnitRunner.class)
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
