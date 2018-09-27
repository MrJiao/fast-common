package com.jackson.common.properties.example;



/**
 * Create by: Jackson
 */
public class TemplateUtil {


    public static void temp(){
        /*try {
            //创建Freemarker配置实例
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

            TemplateManager templateManager = new TemplateManager();
            String templateContent = templateManager.getTemplateStr("properties");


           // TemplateLoader classTemplateLoader = new ClassTemplateLoader(TemplateUtil.class, "/");
            StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
            stringTemplateLoader.putTemplate("properties",templateContent);

            cfg.setTemplateLoader(stringTemplateLoader);
            //cfg.setDirectoryForTemplateLoading(new File(PathTemp.class.getResource("/").getPath()));
            cfg.setDefaultEncoding("UTF-8");
            //cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            //创建数据模型
            Map root = new HashMap();
            root.put("user", "老高");
            //加载模板文件
            Template t1 = cfg.getTemplate("properties");
            //显示生成的数据,//将合并后的数据打印到控制台
            Writer out = new OutputStreamWriter(System.out);
            t1.process(root, out);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }*/

    }

}
