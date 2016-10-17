package com.melinkr.common.rpc;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * <pre>远程服务注解</pre>
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 2016/9/9.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RpcService {
    String value() default "";
}
