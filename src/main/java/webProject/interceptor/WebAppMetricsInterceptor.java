package webProject.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * interceptor MVC per collezionare l'elenco di tutti i processi dei controlle,
 * catturandoli tramite un interceptor
 * 
 */

/*Quando esiste un task comune a più classi java e si vuole evitare di modificare a seguito di cambiamenti ogni singola 
 * classe si possono utilizzare gli interceptors.Un tipico esempio è rappresentato dall’operazione 
 * di logging che spesso è richiesta per accedere a diversi servizi offerti.*/
public class WebAppMetricsInterceptor extends HandlerInterceptorAdapter {
    private static Logger LOG = LoggerFactory.getLogger(WebAppMetricsInterceptor.class);
    private long startTime = 0L;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("processing: " + request.getRequestURI() + " Handler: " + handler.toString());
        startTime = System.currentTimeMillis();
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        LOG.info("processed: " + request.getRequestURI() + " Handler: " + handler.toString());
        long responseTime = System.currentTimeMillis() - startTime;
        LOG.info(String.format("responseTime: %d ms", responseTime));
    }
}
