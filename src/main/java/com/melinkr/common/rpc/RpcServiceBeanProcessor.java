package com.melinkr.common.rpc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 2016/9/12.
 */
public class RpcServiceBeanProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        RpcService rpcService = AnnotationUtils.findAnnotation(bean.getClass(), RpcService.class);
        if (rpcService != null) {
            for (Method method : bean.getClass().getDeclaredMethods()) {
                if (!Modifier.isPublic(method.getModifiers())) {
                    continue;
                }
                if (!chechType(method.getReturnType())) {
                    throw new IllegalStateException("@RpcService 注解的类public方法返回类型只能是原子类型或Serializable接口实现类");
                }
                for (Class<?> clazz : method.getParameterTypes()) {
                    if (!chechType(clazz)) {
                        throw new IllegalStateException("@RpcService 注解的类public参数只能是原子类型或Serializable接口实现类");
                    }
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private boolean chechType(Class<?> clazz) {
        return clazz.isPrimitive() || ClassUtils.isAssignable(Serializable.class, clazz);
    }
}
