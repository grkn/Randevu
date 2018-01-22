package com.hizliyol.core.util;

public class Util {
	
    public static String getStackTrace(StackTraceElement[] arr){
        StringBuffer builder = new StringBuffer();
        for (StackTraceElement element :arr) {
            builder.append(element.toString()).append("\n");
        }
        return builder.toString();
    }
}
