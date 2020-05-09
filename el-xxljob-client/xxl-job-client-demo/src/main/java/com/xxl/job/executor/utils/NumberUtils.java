package com.xxl.job.executor.utils;

import java.util.regex.Pattern;

/**
 * @author Roman.Zhang
 * @date 2020/5/9
 * @description:
 */
public class NumberUtils {
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

}
