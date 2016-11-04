package com.melinkr.common.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 2016/9/12.
 */
public class RpcServiceBeanProcessor implements BeanPostProcessor {
    private final static Logger LOGGER = LoggerFactory.getLogger(RpcServiceBeanProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        /*
        RpcService rpcService = AnnotationUtils.findAnnotation(bean.getClass(), RpcService.class);
        if (rpcService != null) {
            for (Method method : bean.getClass().getDeclaredMethods()) {
                if (!Modifier.isPublic(method.getModifiers())) {
                    continue;
                }
                if (!chechType(method.getReturnType())) {
                    LOGGER.error("{}#{}, returnType={}", bean.getClass(), method.getName(), method.getReturnType());
                    throw new IllegalStateException("@RpcService 注解的类public方法返回类型只能是原子类型、Serializable、Set、Map、Collection接口实现类");
                }
                for (Class<?> clazz : method.getParameterTypes()) {
                    if (!chechType(clazz)) {
                        LOGGER.error("{}#{}, parameterType={}", bean.getClass(), method.getName(), clazz);
                        throw new IllegalStateException("@RpcService 注解的类public参数只能是原子类型、Serializable、Set、Map、Collection接口实现类");
                    }
                }
            }
        }
        */
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private boolean chechType(Class<?> clazz) {
        return clazz.isPrimitive()
                || ClassUtils.isAssignable(Set.class, clazz)
                || ClassUtils.isAssignable(Map.class, clazz)
                || ClassUtils.isAssignable(Collection.class, clazz)
                || ClassUtils.isAssignable(Serializable.class, clazz)
        ;
    }
}
