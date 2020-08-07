package com.cskaoyan.util.string;

public class StringUtil {

    public static String upperCaseFirstLatter(String str){
        char[] strChar=str.toCharArray();
        strChar[0]-=32;
        return String.valueOf(strChar);
    }

}
