package com.amido.graphqltest.configuration;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.oembedler.moon.graphql.boot.ClasspathResourceSchemaStringProvider;
import com.oembedler.moon.graphql.boot.SchemaStringProvider;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.List;

@TestConfiguration
public class GraphQlTestTemplateConfiguration {

//    @Bean
//    public GraphQLTestTemplate graphQLTestTemplate() {
//        return new GraphQLTestTemplate();
//    }

    @Bean
    public SchemaStringProvider schemaStringProvider(){
        return new ClasspathResourceSchemaStringProvider();
    }

}
