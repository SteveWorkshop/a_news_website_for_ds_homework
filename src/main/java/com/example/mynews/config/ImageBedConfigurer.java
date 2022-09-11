package com.example.mynews.config;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//图床配置类
@Configuration
public class ImageBedConfigurer implements WebMvcConfigurer {
    @Value("${cbs.imagesPath}")
    private String mImagesPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        if (mImagesPath.equals("") || mImagesPath.equals("${cbs.imagesPath}"))
        {
            String imagesPath = ImageBedConfigurer.class.getClassLoader().getResource("").getPath();
            if (imagesPath.indexOf(".jar") > 0)
            {
                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
            }
            else if (imagesPath.indexOf("classes") > 0)
            {
                imagesPath = "file:" + imagesPath.substring(0, imagesPath.indexOf("classes"));
            }
            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/")) + "/images/";
            mImagesPath = imagesPath;
        }
        LoggerFactory.getLogger(ImageBedConfigurer.class).info("imagesPath=" + mImagesPath);
        registry.addResourceHandler("/images/**").addResourceLocations(mImagesPath);
    }
}
