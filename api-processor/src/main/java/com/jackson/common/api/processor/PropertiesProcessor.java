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
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;
import java.util.Set;

/**
 * Create by: Jackson
 */
//@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"com.jackson.common.api.annotation.PropertiesBean"})
public class PropertiesProcessor extends AbstractProcessor {

    //在这里执行处理过程，可能会被调用多次
    //return true:注解生成了其他新的注解 false:没有
    boolean isFirst = true;
    int time = 1;
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        //创建动态代码，实际上就是创建一个String, 写入到文件里
        //然后文件会被解释为.class文件

        StringBuilder builder = new StringBuilder()
                .append("package com.jackson.common.api;\n\n")
                .append("public class GeneratedClass {\n\n")
                .append("\tpublic String getMessage() {\n")
                .append("\t\treturn \"");

        //获取所有被CustomAnnotation修饰的代码元素
        for (Element element : roundEnvironment.getElementsAnnotatedWith(PropertiesBean.class)) {
            List<? extends Element> enclosedElements = element.getEnclosedElements();
            Element enclosingElement = element.getEnclosingElement();
            List<? extends AnnotationMirror> annotationMirrors = element.getAnnotationMirrors();
            Set<Modifier> modifiers = element.getModifiers();

            String objectType = element.getSimpleName().toString();
            builder.append(objectType).append(" exists!\\n");
        }
        builder.append("\";\n")
                .append("\t}\n")
                .append("}\n");

        //将String写入并生成.class文件
        try {
            JavaFileObject source = processingEnv.getFiler().createSourceFile(
                    "com.jackson.common.api.GeneratedClass");

            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            //
        }

        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return super.getSupportedAnnotationTypes();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }
}
