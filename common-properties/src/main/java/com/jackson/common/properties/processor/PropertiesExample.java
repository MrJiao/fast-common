package com.jackson.common.properties.processor;


import com.google.auto.service.AutoService;
import com.jackson.common.properties.TemplateManager;
import com.jackson.common.properties.annotation.PropertiesBean;
import com.jackson.common.properties.domain.PropertiesField;
import com.jackson.common.properties.domain.PropertiesMapper;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.Writer;
import java.util.Set;

/**
 * Create by: Jackson
 */
@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)//这里写支持的版本
@SupportedAnnotationTypes(value = {"com.jackson.common.properties.annotation.PropertiesBean"})//这里写这个处理器可以处理的注解
public class PropertiesExample extends AbstractProcessor {

    //在这里执行处理过程，可能会被调用多次
    //return true:注解生成了其他新的注解 false:没有
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        try {

            for (Element element : roundEnvironment.getElementsAnnotatedWith(PropertiesBean.class)) {
                PropertiesMapper propertiesMapper = getPropertiesMapper();
                JavaFileObject source = processingEnv.getFiler().createSourceFile(
                        propertiesMapper.getPackageName() + "." + propertiesMapper.getClassName());
                Writer writer = source.openWriter();

                TemplateManager templateManager = new TemplateManager();
                String fileName = "temp.ftl";
                templateManager.process(fileName, propertiesMapper, writer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    private PropertiesMapper getPropertiesMapper() {
        PropertiesMapper propertiesMapper = new PropertiesMapper();
        propertiesMapper.setClassName("Temp_Properties");
        propertiesMapper.setPackageName("com.jackson.common.api");
        propertiesMapper.addField(new PropertiesField("String","field1"));
        propertiesMapper.addField(new PropertiesField("int","field2"));
        propertiesMapper.addField(new PropertiesField("float","field3"));
        return propertiesMapper;
    }


}
