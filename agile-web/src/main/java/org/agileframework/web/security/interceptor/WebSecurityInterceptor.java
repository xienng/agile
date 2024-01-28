package org.agileframework.web.security.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.agileframework.web.security.annotation.SecurityIgnore;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * [简要描述类用途]
 * <p>
 * [详细描述类用途、功能等，可选]
 * <p>
 * [额外的细节描述，比如类的组成、原理等，可选]
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月27日 21:32
 */
public class WebSecurityInterceptor implements HandlerInterceptor, ApplicationListener<ContextRefreshedEvent> {
    private final Set<HandlerMethod> securityIgnoreHandlers = new LinkedHashSet<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //RequestMappingHandlerMapping收集了所有的控制器
        //遍历所有控制器，捞出有@NSecurityIgnore注解的HandlerMethod
        event.getApplicationContext().getBean(RequestMappingHandlerMapping.class).getHandlerMethods().values().forEach(handlerMethod -> {
            SecurityIgnore anno = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), SecurityIgnore.class);
            if (anno == null) {
                anno = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), SecurityIgnore.class);
            }
            if (anno != null) {
                securityIgnoreHandlers.add(handlerMethod);
            }
        });
    }
}
