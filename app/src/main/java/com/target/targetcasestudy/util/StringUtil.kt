package com.target.targetcasestudy.util

object StringUtil {
    fun isNullOrEmpty(str: String?): Boolean {
        return if (str == null) {
            true
        } else "" == str.trim { it <= ' ' }
    }
}