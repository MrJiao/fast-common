package com.jackson.common.api;

import com.jackson.common.api.domain.MyPropertiesBean_Properties;

import java.io.File;

/**
 * Create by: Jackson
 */
public class Maion {


    public static void main(String[] args){
        File file = new File("aa");
        String absolutePath = file.getAbsolutePath();
        MyPropertiesBean_Properties myPropertiesBean = new MyPropertiesBean_Properties(file);
        myPropertiesBean.storeA(12);
        myPropertiesBean.storeB(32l);
        myPropertiesBean.storeName("张三");
    }

}
