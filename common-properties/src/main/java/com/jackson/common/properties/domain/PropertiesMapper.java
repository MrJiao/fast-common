package com.jackson.common.properties.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by: Jackson
 */
public class PropertiesMapper {


    private String packageName;
    private String className;
    private List<PropertiesField> fieldList = new ArrayList<>(1);

    public PropertiesMapper(String packageName, String className) {
        this.packageName = packageName;
        this.className = className+"_Properties";
    }


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<PropertiesField> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<PropertiesField> fieldList) {
        this.fieldList = fieldList;
    }


    public void addField(PropertiesField field){
        fieldList.add(field);
    }
}
