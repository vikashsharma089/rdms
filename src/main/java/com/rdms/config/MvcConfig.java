package com.rdms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);

    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/logout").setViewName("logout");
        registry.addViewController("/forgot-password").setViewName("forgot-password");
        registry.addViewController("/reset-password").setViewName("reset-password");
       /* registry.addViewController("/AccountNotFound").setViewName("AccountNotFound");
        registry.addViewController("/BillingNotFound").setViewName("BillingNotFound");
        registry.addViewController("/ChargeItemNotFound").setViewName("ChargeItemNotFound");
        registry.addViewController("/ClientNotFound").setViewName("ClientNotFound");
        registry.addViewController("/ClientRecordNotFound").setViewName("ClientRecordNotFound");
        registry.addViewController("/ClientStorageInfoNotFound").setViewName("ClientStorageInfoNotFound");
        registry.addViewController("/DeliveryListNotFound").setViewName("DeliveryListNotFound");
        registry.addViewController("/LotInfoNotFound").setViewName("LotInfoNotFound");
        registry.addViewController("/OtherChargeNotFound").setViewName("OtherChargeNotFound");
        registry.addViewController("/StorageTypeNotFound").setViewName("StorageTypeNotFound");
        registry.addViewController("/TallySheetNotFound").setViewName("TallySheetNotFound");
        registry.addViewController("/WareHouseNotFound").setViewName("WareHouseNotFound");
        registry.addViewController("/WSOInfoNotFound").setViewName("WSOInfoNotFound");*/

    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityHeadersInterceptor()).addPathPatterns("/**");
    }

}