package com.jackson.common.properties;

import com.jackson.common.properties.BaseProperties;

import java.io.File;
import java.util.Properties;

/**
 * Create by: Jackson
 */
public class MyProperties extends BaseProperties {

    private String field1;

    public MyProperties(File file){
        Properties properties = init(file);
        field1 = properties.getProperty("field1");
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public void storeField1(String value){
        storeProperties("field1",value);
    }

}
