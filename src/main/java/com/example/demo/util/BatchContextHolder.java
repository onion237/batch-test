package com.example.demo.util;

public class BatchContextHolder {
    private static ThreadLocal<String> dataBaseName = new ThreadLocal<>();

    public static String dataBaseName(){
        return dataBaseName.get();
    }
    public static void setDataBaseName(String id){
        dataBaseName.set(id);
    }

}
