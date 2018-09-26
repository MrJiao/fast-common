package com.jackson.common.api.processor;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Create by: Jackson
 */
public class ProcessorUtil {


    public static String getClassStr(Element element){
        String content = null;
        if(element.getKind() == ElementKind.CLASS) {
            try {
                Field f = element.getClass().getField("sourcefile");
                f.setAccessible(true);
                Object source = f.get(element);
                Method openInputStreamMethod = source.getClass().getMethod("openInputStream");
                openInputStreamMethod.setAccessible(true);
                InputStream is = (InputStream) openInputStreamMethod.invoke(source);
                byte[] buff = new byte[is.available()];
                int read = is.read(buff);
                content = new String(buff, 0, read);
                System.out.println(content);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

}
