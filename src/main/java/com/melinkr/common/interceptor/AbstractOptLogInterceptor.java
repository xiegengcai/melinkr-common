package com.melinkr.common.interceptor;


import com.alibaba.fastjson.JSON;
import com.melinkr.common.annotation.Loggable;
import com.melinkr.common.utils.StringUtil;
import org.aspectj.lang.JoinPoint;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 2016/9/9.
 */
public abstract class AbstractOptLogInterceptor {

    /**
     * 构建属性更新前后内容
     * @param joinPoint
     * @return
     */
    protected String buildContent(JoinPoint joinPoint) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object [] args = joinPoint.getArgs();
        Loggable optLog = getMethod(joinPoint, args).getDeclaredAnnotation(Loggable.class);
        if (optLog.keyAttributes() == null && optLog.keyAttributes().length < 1) {
            return null;
        }
        if (args == null && args.length < 2) {
            throw new RuntimeException("被拦截的方法至少需要两个参数(原对象，更新属性对象)");
        }
        Object before = args[0];
        Object after = args[1];
        if (!before.getClass().equals(after.getClass())) {
            throw new RuntimeException("修改前对象，修改后对象类型不一致");
        }
        StringBuilder content = new StringBuilder();
        for (String attr : optLog.keyAttributes()) {
            String beforeValue = invokeGetter(before, attr);
            String afterValue = invokeGetter(before, attr);
            if (!afterValue.equals(beforeValue)) {

            }
            content.append(";更新").append(attr).append(":")
                    .append(beforeValue).append(">>").append(afterValue);
        }
        return content.delete(0, 1).toString();
    }

    /**
     * 获取当前执行的方法
     * @param joinPoint
     * @return
     */
    private Method getMethod(JoinPoint joinPoint, Object [] args) throws NoSuchMethodException {
        String methodLongName = joinPoint.getSignature().getName();
        Class [] paramTypes = new Class[args.length];
        for (int i = 0; i < args.length; i ++) {
            paramTypes[i] = args[i].getClass();
        }
        return joinPoint.getTarget().getClass().getMethod(methodLongName, paramTypes);
    }

    private String invokeGetter(Object owner, String fieldName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = owner.getClass().getMethod(new StringBuilder("get").append(StringUtil.toUpperCaseFirstOne(fieldName)).toString());
        method.setAccessible(true);
        Object result = method.invoke(owner);
        if (result instanceof String) {
            return (String) result;
        }
        return JSON.toJSONString(result);
    }

}
