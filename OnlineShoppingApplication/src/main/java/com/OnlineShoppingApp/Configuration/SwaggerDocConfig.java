//package com.OnlineShoppingApp.Configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerDocConfig implements WebMvcConfigurer{
//	
//	@Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//        registry
//                .addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/src/main/resources/");
//
//        registry
//                .addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/src/main/resources/static/");
//    }
//	
//	@Bean
//    public Docket apiDocket() {
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(getApiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.OnlineShoppingApp.Controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo getApiInfo() {
//
//        return new ApiInfoBuilder()
//                .title("Swagger API Doc")
//                .description("More description about the API")
//                .version("1.0.0")
//                .build();
//    }
//
//}
