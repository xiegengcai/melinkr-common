package com.melinkr.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PreferencesPlaceholderConfigurer;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.Map;
import java.util.Properties;

/**
 * 自定义资源文件加载
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 2016/10/8.
 * <h4>使用方法：</h4>
 * <pre>
{@code
<bean id="propertyConfigurer" class="com.melinkr.common.config.MelinkrPropertyConfigurer">
    <property name="locations">
        <list>
            <value>classpath*:conf/config.properties</value>
        </list>
    </property>
    <!-- 将"dubbo."开头的属性加到系统属性配置方便dubbo直接读取 -->
    <property name="sysConfPrefix" value="dubbo."/>
    <!-- 将".password"或“.pwd”的属性Base64解码 -->
    <property name="base64Pwd" value="true"/>
<bean>
}
 * </pre>
 */
public class MelinkrPropertyConfigurer extends PreferencesPlaceholderConfigurer {
    private final Logger LOG = LoggerFactory.getLogger(MelinkrPropertyConfigurer.class);

    /**
     * 需要加到系统系统配置的属性前缀{@link java.lang.System#setProperty(String, String)}
     */
    private String sysConfPrefix;
    /**
     * 是否对密码做base64编码解码（即对包含“.password”或".pwd"的属性是base64编码后的，读取时要base64解码）
     */
    private boolean base64Pwd=false;

    private static final String FULL_PWD = ".password";
    private static final String SHORT_PWD = ".pwd";

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        boolean customized = StringUtils.hasText(sysConfPrefix);
        // 处理自定义配置
        for (Map.Entry<Object, Object> entry : props.entrySet()) {
            // System config contains this key, ignore
            if (System.getProperties().containsKey(entry.getKey())) {
                continue;
            }

            String keyStr = entry.getKey().toString();
            String value = entry.getValue().toString();
            // 处理密码Base64解码
            String lowerKey = keyStr.toLowerCase();
            if (lowerKey.indexOf(FULL_PWD) != -1
                    || lowerKey.indexOf(SHORT_PWD) != -1) {
                value = new String(Base64.getDecoder().decode(value));
                props.put(keyStr, value);
            }
            if (customized && keyStr.startsWith(sysConfPrefix)) {
                System.setProperty(keyStr, value);
            }

            LOG.debug("{}={}", keyStr, value);
        }
        super.processProperties(beanFactoryToProcess, props);
    }

    public void setSysConfPrefix(String sysConfPrefix) {
        this.sysConfPrefix = sysConfPrefix;
    }

    public void setBase64Pwd(boolean base64Pwd) {
        this.base64Pwd = base64Pwd;
    }
}
