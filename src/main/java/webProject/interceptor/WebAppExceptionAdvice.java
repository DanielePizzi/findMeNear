package webProject.interceptor;

import webProject.framework.api.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Gestore delle eccezioni sui controller.Serve per catturare tutte le eccezzioni proveniente dai 
 * controller
 * 
 */
@ControllerAdvice
public class WebAppExceptionAdvice {
    private static Logger LOG = LoggerFactory.getLogger(WebAppExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public APIResponse handleAnyException(Exception e) {
        LOG.error(e.getMessage());
        e.printStackTrace();
        return APIResponse.toErrorResponse(e.getMessage());
    }
}
