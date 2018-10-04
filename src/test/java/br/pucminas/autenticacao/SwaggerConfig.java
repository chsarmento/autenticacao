package br.pucminas.autenticacao;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());

    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Livraria virtual REST API", "O seu objetivo é implementar uma API de autenticação para a gestão de uma livraria virtual. Uma livraria virtual deve ter pelo menos funcionalidades para:\r\n"
                + "Cadastro de novos livros, Cadastro de autores, Cadastro de editoras. Pesquisa de livros por critérios diversos, Postar comentários para livros, Manipular um carrinho de compras, Realizar pedidos, Acompanhamento o status das entregas realizadas..", "API", "Terms of service",
                new Contact("Christopher Sarmento", "www.pucminas.com.br", "chsarmento@gmail.com"), "License of API",
                "API license URL", Collections.emptyList());
    }

}
