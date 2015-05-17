package by.itechart.flowerty.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 */
// TODO: this class unnecessary. it will be removed in future.
@Controller
class CustomErrorController {
    @ResponseBody
    @RequestMapping("error")
    public String generalError(HttpServletRequest request, HttpServletResponse response, Model model) {
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
//        String exceptionMessage = getExceptionMessage(throwable, statusCode);
//
//        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
//        if (requestUri == null) {
//            requestUri = "Unknown";
//        }
//
//        String message = MessageFormat.format("{0} returned for {1} with message {2}", statusCode, requestUri,
//                exceptionMessage);
//
//        model.addAttribute("errorMessage", message);

        return "error/general";
    }

    @ResponseBody
    @RequestMapping("/403")
    public String accessDeniedError(HttpServletRequest request, HttpServletResponse response, Model model) {
//        if (request != null) {
//            throw new IllegalArgumentException();
//        }
//        return new AccessDeniedException("balbalba");
        return "redirect:/#/error";
    }

//    private String getExceptionMessage(Throwable throwable, Integer statusCode) {
//        if (throwable != null) {
//            return Throwables.getRootCause(throwable).getMessage();
//        }
//        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
//
//        return httpStatus.getReasonPhrase();
//    }
}
