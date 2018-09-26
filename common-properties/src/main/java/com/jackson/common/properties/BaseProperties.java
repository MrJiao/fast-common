package com.jackson.common.properties;

import java.io.*;
import java.util.Properties;

/**
 * Create by: Jackson
 */
public class BaseProperties {

    File file;
    public Properties init(File file){
        this.file = file;
        return loadProperties(file);
    }

    public static Properties loadProperties(File file) {
        Properties props = new Properties();

        if (!file.exists()) {
            try {
                boolean newFile = file.createNewFile();
                if (!newFile)
                    throw new RuntimeException("创建配置文件失败:" + file.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException(file.getAbsolutePath(),e);
            }
        }
        try (InputStream inStream = new FileInputStream(file)) {
            props.load(inStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return props;
    }

    protected void storeProperties(String key, String value) {
        if (!file.exists()) throw new RuntimeException("配置文件找不到 file:"+file.getAbsolutePath());
        Properties properties = loadProperties(file);
        properties.setProperty(key, value);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            properties.store(fileOutputStream, "");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("配置文件找不到 file:"+file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("配置文件找不到 file:"+file.getAbsolutePath());
        }finally {
            if(fileOutputStream!=null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
