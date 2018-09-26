package com.jackson.common.properties;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * Create by: Jackson
 */
public class TemplateManager {

    /**
     * 获取resources下的模板数据
     * @param fileName
     * @return
     */
    public  String getTemplateStr(String fileName){
        InputStream inputStream = null;
        try {
            URL location = this.getClass().getProtectionDomain().getCodeSource().getLocation();
            String path = location.getFile();
            //  String path = "/Users/jiaoyubing/.m2/repository/com/jackson/common/api-processor/0.0.1-SNAPSHOT/api-processor-0.0.1-SNAPSHOT.jar";
            JarFile jarFile = new JarFile(path);
            ZipEntry temp = jarFile.getEntry(fileName);
            inputStream = jarFile.getInputStream(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(readStream(inputStream));
    }


    Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
    public void process(String fileName,Object dataModel, Writer out) throws IOException, TemplateException {

        String templateStr = getTemplateStr(fileName);
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate(fileName,templateStr);

        cfg.setTemplateLoader(stringTemplateLoader);
        CharArrayWriter charArrayWriter = new CharArrayWriter();

        Template template = cfg.getTemplate(fileName);
        template.process(dataModel,charArrayWriter);

        String content = charArrayWriter.toString();

        out.write(content);
        out.flush();
        out.close();
    }


    private byte[] readStream(InputStream inputStream)  {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        try {
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return outputStream.toByteArray();
    }

}
