package com.demotuwei.demotuwei.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 处理跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        if (StringUtils.isNotEmpty(draftProperties.getCustomStartPath())) {
//            registry.addResourceHandler(CoreUtilConsts.DOWNLOAD_FILE_URL)
//                    .addResourceLocations("file:" + Paths.get(draftProperties.getCustomStartPath(), draftProperties.getDocPath()).toAbsolutePath().normalize() + File.separator)
//                    .addResourceLocations("file:" + Paths.get(draftProperties.getCustomStartPath(), draftProperties.getDocPathTmp()).toAbsolutePath().normalize() + File.separator)
//                    .addResourceLocations("file:" + Paths.get(draftProperties.getCustomStartPath(), draftProperties.getTemplatePathTmp()).toAbsolutePath().normalize() + File.separator)
//                    .addResourceLocations("file:" + Paths.get(draftProperties.getCustomStartPath(), draftProperties.getTemplatePath()).toAbsolutePath().normalize() + File.separator)
//                    .addResourceLocations("file:" + Paths.get(draftProperties.getCustomStartPath(), draftProperties.getUploadDir()).toAbsolutePath().normalize() + File.separator);
//        } else {
//            registry.addResourceHandler(CoreUtilConsts.DOWNLOAD_FILE_URL)
//                    .addResourceLocations("file:" + Paths.get(draftProperties.getDocPath()).normalize().toAbsolutePath() + File.separator)
//                    .addResourceLocations("file:" + Paths.get(draftProperties.getDocPathTmp()).normalize().toAbsolutePath() + File.separator)
//                    .addResourceLocations("file:" + Paths.get(draftProperties.getTemplatePathTmp()).normalize().toAbsolutePath() + File.separator)
//                    .addResourceLocations("file:" + Paths.get(draftProperties.getTemplatePath()).normalize().toAbsolutePath() + File.separator)
//                    .addResourceLocations("file:" + Paths.get(draftProperties.getUploadDir()).normalize().toAbsolutePath() + File.separator);
//        }
//    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(0, new MappingJackson2HttpMessageConverter());
//    }
}