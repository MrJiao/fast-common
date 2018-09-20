package com.jackson.common.api.temp;

import com.jackson.common.api.annotation.PropertiesBean;

/**
 * Create by: Jackson
 */
@PropertiesBean
public class MyPropertiesBean {


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
