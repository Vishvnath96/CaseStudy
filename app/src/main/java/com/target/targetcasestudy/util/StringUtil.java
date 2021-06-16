package com.target.targetcasestudy.util;

public class StringUtil {


    public static boolean isNullOrEmpty(final String str) {
        if (str == null) {
            return true;
        }
        return "".equals(str.trim());
    }
}
