package com.jackson.common.properties.example;

import java.net.URL;

/**
 * Create by: Jackson
 */
public class TemplateUtil {

    //获取路径的方式
    public void getPath(){
        URL resource = this.getClass().getResource("/");
        URL resource1 = this.getClass().getClassLoader().getResource("/");
        String runtimePath = System.getProperty("user.dir");
        URL systemResource = ClassLoader.getSystemResource("");
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
    }




}
