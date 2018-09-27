package com.jackson.common.properties;

import java.util.HashMap;

/**
 * Create by: Jackson
 */
public class TemplateString {

    HashMap<String,String> map = new HashMap<>();

    public TemplateString(){
        map.put("properties",properties);
        map.put("pathTemp",pathTemp);
    }


    public String get(String key){
        return map.get(key);
    }

    public final String pathTemp  = "${path}";


    public final String properties="package ${packageName};\n" +
            "\n" +
            "import com.jackson.common.properties.BaseProperties;\n" +
            "import com.jackson.common.properties.util.StringUtils;\n" +
            "import java.io.File;\n" +
            "import java.util.Properties;\n" +
            "\n" +
            "public class ${className} extends BaseProperties {\n" +
            "\n" +
            "<#list fieldList as field>\n" +
            "    private ${field.type} ${field.name};\n" +
            "</#list>\n" +
            "\n" +
            "    public ${className}(File file){\n" +
            "        Properties properties = init(file);\n" +
            "\n" +
            "        <#list fieldList as field>\n" +
            "            String ${field.name}Str = properties.getProperty(\"${field.name}\");\n" +
            "            if(!StringUtils.isEmpty(${field.name}Str)){\n" +
            "                this.${field.name} = ${field.paseFunc}(${field.name}Str);\n" +
            "            }\n" +
            "        </#list>\n" +
            "    }\n" +
            "\n" +
            "    <#list fieldList as field>\n" +
            "\n" +
            "     public ${field.type} get${field.name?cap_first}(){\n" +
            "        return ${field.name};\n" +
            "     }\n" +
            "\n" +
            "      public void set${field.name?cap_first}(${field.type} ${field.name}){\n" +
            "        this.${field.name} = ${field.name};\n" +
            "      }\n" +
            "\n" +
            "      public void store${field.name?cap_first}(${field.type} ${field.name}){\n" +
            "        set${field.name?cap_first}(${field.name});\n" +
            "        storeProperties(\"${field.name}\",String.valueOf(${field.name}));\n" +
            "      }\n" +
            "    </#list>\n" +
            "\n" +
            "}\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n";

}
