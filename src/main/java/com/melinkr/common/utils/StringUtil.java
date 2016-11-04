package com.melinkr.common.utils;

import com.melinkr.common.Constant;
import org.springframework.util.Assert;

/**
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 2016/9/9.
 */
public class StringUtil {

    private StringUtil(){}

    public static final String KEY_SEPARATOR = ":";

    /**
     * 构造key字符串
     * @param keyParams
     * @return
     */
    public static String buildKey(String ... keyParams) {
        Assert.notEmpty(keyParams, "keyParams不能为空");
        StringBuilder builder = new StringBuilder();
        for (String key : keyParams) {
            builder.append(KEY_SEPARATOR).append(key);
        }
        return builder.delete(0, 1).toString();
    }

    /**
     *
     * @param model 模块名称
     * @param key key
     * @return model+KEY_SEPARATOR+key
     */
    public static String buildKey(String model, String key) {
        return new StringBuilder(model).append(KEY_SEPARATOR).append(key).toString();
    }
    //首字母转小写
    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    //首字母转大写
    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 拼接SQL Like条件
     * @param value
     * @param likeType {@link com.melinkr.common.Constant.LikeType} PRE_MATCH %val, POST_MATCH val%, BOTH %val%
     * @return
     */
    public static String sqlLikeConcat(String value, Constant.LikeType likeType) {
        StringBuilder like = new StringBuilder();
        if (likeType == Constant.LikeType.BOTH || likeType == Constant.LikeType.PRE_MATCH) {
            like.append("%");
        }
        like.append(value);
        if (likeType == Constant.LikeType.BOTH || likeType == Constant.LikeType.POST_MATCH) {
            like.append("%");
        }
        return like.toString();
    }
}
