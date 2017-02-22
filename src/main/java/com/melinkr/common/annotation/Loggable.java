package com.melinkr.common.annotation;

import java.lang.annotation.*;

/**
 * <pre>操作日志注解</pre>
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 2016/9/9.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Loggable {

    /**
     * 操作类型
     * @return
     */
    int optType();
}
