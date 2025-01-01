package com.cf.huaban.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())//用手生成API信息
                .select()// select()函数返回个 ApiSelectorBuilder实例，用来控制接口被swagger做成文档
                .apis(RequestHandlerSelectors.basePackage("com.cf.huaban"))//用于指定扫描哪个包下的接口
                .paths(PathSelectors.any())//选择所有的API,如果 你想只为部分API生成文档，可以配置这里
                .build();
    }
    /**
     *用于定义API主界面的信息，比如可以声明所有的API的总标题、描述、版本
     *@return
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("花瓣评分")//可以用来自定 义API的主标题
                .description("进行基础的平路交流")//可以用来描述 整体的API
                .termsOfServiceUrl("")//用于定义服务的域名
                .version("1.0")//可以用来定义版本。
                .contact(new springfox.documentation.service.Contact("cf","",""))
                .build();//
    }
}