package com.melinkr.common.interceptor;


import com.melinkr.common.annotation.Loggable;
import com.melinkr.common.model.BaseEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 2016/9/9.
 */
public interface AbstractOptLogInterceptor<ID extends Serializable, T extends BaseEntity<ID>, L extends BaseEntity, R> {

    @Around("execution(* *(..)) && @annotation(com.melinkr.common.annotation.Loggable)")
    default R doInvoke(ProceedingJoinPoint joinPoint) throws Throwable{
        Object [] args = joinPoint.getArgs();
        R r = null;
        if (args != null && args.length >= 3) {
            try {
                T t = (T) args[0];
                T before = getBefore(t);
                r = (R) joinPoint.proceed();
                T after = getAfter(t);
                Loggable loggable = getMethod(joinPoint, args).getDeclaredAnnotation(Loggable.class);
                saveLog(buildLog(before, after, loggable.optType(), args[1].toString(), args[2].toString()));
            } catch (ClassCastException e) {
                throw new IllegalArgumentException(String.format("第一个参数必须与泛型T一致"));
            }
        } else {
            throw new IllegalArgumentException("至少需要三个入参(T, String, String)");
        }
        return r;
    }

    /**
     * 获取当前执行的方法
     * @param joinPoint
     * @return
     */
    default Method getMethod(JoinPoint joinPoint, Object [] args) throws NoSuchMethodException {
        String methodLongName = joinPoint.getSignature().getName();
        Class [] paramTypes = new Class[args.length];
        for (int i = 0; i < args.length; i ++) {
            paramTypes[i] = args[i].getClass();
        }
        return joinPoint.getTarget().getClass().getMethod(methodLongName, paramTypes);
    }

    /**
     * 构建操作日志方法。此方法在{@link AbstractOptLogInterceptor#doInvoke(ProceedingJoinPoint)}中调用
     * @param before
     * @param after
     * @param optType 操作类型
     * @param operatorId 操作人ID
     * @param operatorName 操作人名称
     * @return
     */
    L buildLog(T before, T after, int optType, String operatorId, String operatorName);

    /**
     * 保存日志实现, 此方法在{@link AbstractOptLogInterceptor#doInvoke(ProceedingJoinPoint)}中调用
     * @param log
     */
    void saveLog(L log);

    /**
     * 获取修改前记录实现。由具体实现决定按什么方式获取到（id、唯一key或多个唯一组合键)
     * @param t
     * @return
     */
    T getBefore(T t);

    /**
     * 获取修改后记录实现。由具体实现决定按什么方式获取到（id、唯一key或多个唯一组合键)
     * @param t
     * @return
     */
    T getAfter(T t);
}
