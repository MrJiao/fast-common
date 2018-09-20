package com.jackson.common.api.processor;


import com.google.auto.service.AutoService;
import com.jackson.common.api.annotation.PropertiesBean;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Create by: Jackson
 */
@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes(value = {"com.jackson.common.api.annotation.PropertiesBean"})
public class PropertiesProcessor2 extends AbstractProcessor {

    //在这里执行处理过程，可能会被调用多次
    //return true:注解生成了其他新的注解 false:没有
    boolean isFirst = true;
    int time = 1;
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        JavaFileObject classFile;
        Filer filer = processingEnv.getFiler();
        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(PropertiesBean.class);
        for (Element element : elementsAnnotatedWith) {
            String className = element.getSimpleName().toString();
            List<? extends Element> enclosedElements = element.getEnclosedElements();
            Element[] elements = new Element[enclosedElements.size()];
            elements = enclosedElements.toArray(elements);
            try {
                classFile = filer.createSourceFile("My" + className, elements);
                Writer writer = classFile.openWriter();
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

       // processingEnv.

        return false;
    }


    private void printError(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv, Exception e) {
        /*
         * TODO Error printing might need a bit of cleanup. Those tricks are
         * used so that we are sure the compilation error is given to the user.
         * Eclipse compiler wouldn't show it otherwise (as far as I can tell). I
         * know it sucks, any better solution is welcome.
         */
        Messager messager = processingEnv.getMessager();
        Throwable rootCause = e;
        while (rootCause.getCause() != null) {
            rootCause = rootCause.getCause();
        }

        StackTraceElement firstElement = e.getStackTrace()[0];
        StackTraceElement rootFirstElement = rootCause.getStackTrace()[0];
        String errorMessage = e.toString() + " " + firstElement.toString() + " root: " + rootCause.toString() + " " + rootFirstElement.toString();

        messager.printMessage(Diagnostic.Kind.ERROR, "Unexpected annotation processing exception: " + errorMessage);
        e.printStackTrace();

        Element element = roundEnv.getElementsAnnotatedWith(annotations.iterator().next()).iterator().next();
        messager.printMessage(Diagnostic.Kind.ERROR, "Unexpected annotation processing exception (not related to this element, but otherwise it wouldn't show up in eclipse) : " + errorMessage, element);
    }

}
