package com.apiversion.demo.hander;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
 
import java.lang.reflect.Method;
 
/**
 * 版本号匹配拦截器
 * @author Zac
 */
public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
 
    @Override protected RequestCondition<ApiVersionCondition> getCustomTypeCondition(Class<?> handlerType) {
        /**
         * 通过注解获取 apiVersion 的 value 值
         */
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        System.out.println(apiVersion);
        return createCondition(apiVersion);
    }
 
    @Override protected RequestCondition<ApiVersionCondition> getCustomMethodCondition(Method method) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        return createCondition(apiVersion);
    }
 
    private RequestCondition<ApiVersionCondition> createCondition(ApiVersion apiVersion) {
        return apiVersion == null ? null : new ApiVersionCondition(apiVersion.value());
    }
 
 
}