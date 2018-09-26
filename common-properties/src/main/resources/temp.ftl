package ${packageName};

import com.jackson.common.properties.BaseProperties;
import java.io.File;
import java.util.Properties;

public class ${className} extends BaseProperties {

<#list fieldList as field>
    private ${field.type} ${field.name};
</#list>

    public ${className}(File file){
        Properties properties = init(file);

        <#list fieldList as field>
            ${field.name} = ${field.paseFunc}(properties.getProperty("${field.name}"));
        </#list>
    }

    <#list fieldList as field>

     public ${field.type} get${field.name?cap_first}(){
        return ${field.name};
     }

      public void set${field.name?cap_first}(${field.type} ${field.name}){
        this.${field.name} = ${field.name};
      }

      public void store${field.name?cap_first}(${field.type} value){
        storeProperties("${field.name}",value+"");
      }
    </#list>

}




