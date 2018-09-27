package com.jackson.common.properties.util;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * Create by: Jackson
 */
public class ProcessUtil {

    public static void printError(Set<? extends TypeElement> annotations, ProcessingEnvironment processingEnv, RoundEnvironment roundEnv, Exception e) {
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
