package com.melinkr.common.utils;

import com.melinkr.common.Constant;

/**
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 2016/9/9.
 */
public class StringUtil {
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
