package com.esen.swglpt.common.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2 {

    // 接口版本号
    private final String version = "1.0";

    // 接口大标题
    private final String title = "税务管理平台申报系统";

    // 具体的描述
    private final String description = "API文档自动生成示例";

    // 服务说明url
    private final String termsOfServiceUrl = "";

    // license
    private final String lincense = "";

    // license url
    private final String licenseUrl = "";

    // 接口作者联系方式
    private final Contact contact = new Contact("wangd2", "https://github.com/JohnWangv7", "wangd2@esensoft.com");

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 指定controller存放的目录路径
                .apis(RequestHandlerSelectors.basePackage("com.esensoft.swglpt.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title(title)
                // 文档描述
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                // 版本号
                .version(version)
                .contact(contact)
                .build();
    }
}
