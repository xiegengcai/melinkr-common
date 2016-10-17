package com.melinkr.common.utils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * <pre>
 *     参数摘要算法。
 * </pre>
 * <ol>
 *     <li>参数按key字符默认排序</li>
 *     <li>{@link #sign(Map, List, String)} 如有noSignParamNames，排除掉</li>
 *     <li>将上一步得到所有参数按key+value拼接起来</li>
 *     <li>将上一步的结果字符串首尾加盐 secret {@link #sign(Map, List, String)}</li>
 *     <li>只保留a-zA-Z0-9的字符来签名{@link #getSHA1Digest(String)}</li>
 * </ol>
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 2016/8/20.
 */
public class SignUtils {
    /**
     * @param params
     *            需要签名的参数名
     * @param params
     *            参数列表
     * @param secret
     * @return
     */
    public static String sign(Map<String, String> params, String secret) {
        return sign(params, null, secret);
    }

    /**
     * 对paramValues进行签名，其中noSignParamNames这些参数不参与签名
     *
     * @param params
     * @param noSignParamNames
     * @param secret
     * @return
     */
    public static String sign(Map<String, String> params,
                              List<String> noSignParamNames, String secret) {
        try {
            StringBuilder buffer = new StringBuilder();
            List<String> paramNames = new ArrayList<>(params.size());
            paramNames.addAll(params.keySet());
            if (noSignParamNames != null && noSignParamNames.size() > 0) {
                noSignParamNames.forEach(paramNames::remove);
            }
            Collections.sort(paramNames);

            buffer.append(secret);
            for (String paramName : paramNames) {
                buffer.append(paramName).append(params.get(paramName));
            }
            buffer.append(secret);
            byte[] sha1Digest = getSHA1Digest(buffer.toString());
            return byte2hex(sha1Digest);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 只留下a-zA-Z0-9的字符来签名，更方便兼容其他语言
     * @param data
     */
    private static byte[] getSHA1Digest(String data) throws IOException {
        data = Pattern.compile("[^a-zA-Z0-9]").matcher(data).replaceAll("");
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            bytes = md.digest(data.getBytes("UTF-8"));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse);
        }
        return bytes;
    }

    /**
     * 二进制转十六进制字符串
     *
     * @param bytes
     */
    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }
}
