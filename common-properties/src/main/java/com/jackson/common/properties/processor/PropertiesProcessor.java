package com.jackson.common.properties.processor;


import com.google.auto.service.AutoService;
import com.jackson.common.properties.TemplateManager;
import com.jackson.common.properties.annotation.PropertiesBean;
import com.jackson.common.properties.domain.PropertiesField;
import com.jackson.common.properties.domain.PropertiesMapper;
import com.jackson.common.properties.util.ProcessUtil;
import com.sun.tools.javac.code.*;
import com.sun.tools.javac.util.Filter;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
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
public class PropertiesProcessor extends AbstractProcessor {

    //在这里执行处理过程，可能会被调用多次
    //return true:注解生成了其他新的注解 false:没有
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        try {

            for (Element element : roundEnvironment.getElementsAnnotatedWith(PropertiesBean.class)) {
                if(element.getKind() == ElementKind.CLASS){
                    Symbol.ClassSymbol classElement = (Symbol.ClassSymbol) element;
                    Iterable<Symbol> fieldElements = classElement.members().getElements(new Filter<Symbol>() {
                        @Override
                        public boolean accepts(Symbol symbol) {
                            return symbol.getKind() == ElementKind.FIELD;
                        }
                    });

                    Symbol.PackageSymbol packge = classElement.packge();
                    String packageName = packge.getQualifiedName().toString();
                    String className = classElement.getQualifiedName().toString().substring(packageName.length()+1);
                    PropertiesMapper propertiesMapper = new PropertiesMapper(packageName,className);
                    for (Symbol fieldSymbol : fieldElements) {
                        if(fieldSymbol.getKind() == ElementKind.FIELD)
                            propertiesMapper.addField(new PropertiesField(fieldSymbol.asType().asElement().getSimpleName().toString(),fieldSymbol.getSimpleName().toString()));
                    }
                    JavaFileObject source = processingEnv.getFiler().createSourceFile(
                            propertiesMapper.getPackageName() + "." + propertiesMapper.getClassName());
                    Writer writer = source.openWriter();

                    TemplateManager templateManager = new TemplateManager();
                    String fileName = "properties";
                    templateManager.process(fileName, propertiesMapper, writer);
                }
            }

        } catch (Exception e) {
            ProcessUtil.printError(annotations,processingEnv,roundEnvironment,e);
        }

        return false;
    }



}
