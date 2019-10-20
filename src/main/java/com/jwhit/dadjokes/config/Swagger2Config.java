package com.jwhit.dadjokes.config;

import com.fasterxml.classmate.TypeResolver;
import com.jwhit.dadjokes.models.APIOpenLibrary;
import com.jwhit.dadjokes.models.ErrorDetail;
import com.jwhit.dadjokes.models.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// http://localhost:2019/swagger-ui.html
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config
{
    @Autowired
    private TypeResolver resolver;

    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2).select()
                                                      //                .apis(RequestHandlerSelectors.any())
                                                      .apis(RequestHandlerSelectors.basePackage("com.jwhit"))
                                                      .paths(PathSelectors.any())
                                                      .build()
                                                      .useDefaultResponseMessages(false) // Allows only my exception responses
                                                      .ignoredParameterTypes(Pageable.class) // allows only my paging parameter list
                                                      .apiInfo(apiEndPointsInfo())
                                                      .pathMapping("/")
                                                      .additionalModels(resolver.resolve(APIOpenLibrary.class),
                                                                        resolver.resolve(TokenModel.class),
                                                                        resolver.resolve(ErrorDetail.class))
                                                      .ignoredParameterTypes(SimpleGrantedAuthority.class);
    }

    private ApiInfo apiEndPointsInfo()
    {
        return new ApiInfoBuilder().title("Dad Joke Build Week Project")
                                   .description("The only place for TOP QUALITY dad jokes!")
                                   .contact(new Contact("Jeffrey Whitaker - Backend Dev",
                                                        "http://www.lambdaschool.com",
                                                        "jeffrey.david.whitaker@zoho.com"))
                                   .license("MIT License")
                                   .licenseUrl("https://github.com/jeffreywhitaker/dadjoke-java-backend/blob/master/LICENSE")
                                   .version("1.0.0")
                                   .build();
    }
}
