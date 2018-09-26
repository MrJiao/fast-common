package com.jackson.common.api.processor;


import com.google.auto.service.AutoService;
import com.jackson.common.api.annotation.PropertiesBean;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Set;

/**
 * Create by: Jackson
 */
//@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)//这里写支持的版本
@SupportedAnnotationTypes(value = {"com.jackson.common.api.annotation.PropertiesBean"})//这里写这个处理器可以处理的注解
public class PropertiesExample extends AbstractProcessor {

    //在这里执行处理过程，可能会被调用多次
    //return true:注解生成了其他新的注解 false:没有
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        TemplateUtil.temp();

        return false;
    }


}
