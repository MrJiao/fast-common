package com.jackson.common.properties.domain;

import com.jackson.common.properties.util.StringUtils;

/**
 * Create by: Jackson
 */
public class PropertiesField {


    public PropertiesField(String type, String name) {
        this.type = type;
        this.name = name;
        initParseFunc(type);
    }

    private void initParseFunc(String type) {
        if(StringUtils.equals(type,"int")|| StringUtils.equals(type,"Integer")||StringUtils.equals(type,"java.lang.Integer")){
            paseFunc = "Integer.parseInt";
        }else if(StringUtils.equals(type,"long")|| StringUtils.equals(type,"Long")|| StringUtils.equals(type,"java.lang.Long")){
            paseFunc = "Long.parseLong";
        }else if(StringUtils.equals(type,"byte")|| StringUtils.equals(type,"Byte")|| StringUtils.equals(type,"java.lang.Byte")){
            paseFunc = "Byte.parseByte";
        }else if(StringUtils.equals(type,"double")|| StringUtils.equals(type,"Double")|| StringUtils.equals(type,"java.lang.Double")){
            paseFunc = "Double.parseDouble";
        }else if(StringUtils.equals(type,"float")|| StringUtils.equals(type,"Float")|| StringUtils.equals(type,"java.lang.Float")){
            paseFunc = "Float.parseFloat";
        }else if(StringUtils.equals(type,"short")|| StringUtils.equals(type,"Short")|| StringUtils.equals(type,"java.lang.Short")){
            paseFunc = "Short.parseShort";
        }else if(StringUtils.equals(type,"boolean")|| StringUtils.equals(type,"Boolean")|| StringUtils.equals(type,"java.lang.Boolean")){
            paseFunc = "Boolean.parseBoolean";
        }else {
            paseFunc="("+type+")";
        }
    }

    private String type;
    private String name;
    private String paseFunc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaseFunc() {
        return paseFunc;
    }
}
