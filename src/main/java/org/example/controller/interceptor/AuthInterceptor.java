package org.example.controller.interceptor;

import org.example.service.AccessTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zeymo
 */
@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor, BeanPostProcessor {
    private final AccessTokenService accessTokenService;
    private final Set<String> needAuthUri = new HashSet<>(100);

    @Autowired
    public AuthInterceptor(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!isNeedAuthRequest(request)) {
            return true;
        }

        if (accessTokenService.isTokenNearlyExpired()) {
            // server token expired, not client, so not return 401
            response.setStatus(500);
            response.getWriter().write("server token is expired, please try later.");
            log.info("request block by token expired, uri={}.", request.getRequestURI());
            return false;
        }

        return true;
    }

    private boolean isNeedAuthRequest(HttpServletRequest request) {
        return needAuthUri.contains(request.getRequestURI());
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (!beanClass.isAnnotationPresent(RestController.class)) {
            return bean;
        }

        if (!beanClass.isAnnotationPresent(TokenRequired.class)) {
            return bean;
        }

        String root = "";
        if (beanClass.isAnnotationPresent(RequestMapping.class)) {
            root = beanClass.getAnnotation(RequestMapping.class).value()[0];
        }

        for (Method method : bean.getClass().getMethods()) {
            String methodPath;
            if (method.isAnnotationPresent(PostMapping.class)) {
                methodPath = method.getAnnotation(PostMapping.class).value()[0];
            } else if (method.isAnnotationPresent(GetMapping.class)) {
                methodPath = method.getAnnotation(GetMapping.class).value()[0];
            } else {
                continue;
            }

            String completePath = Paths.get(root, methodPath).toString();
            needAuthUri.add(completePath);
            log.info("AuthInterceptor add need auth path={}", completePath);
        }

        return bean;
    }
}
