package com.amido.graphqltest.configuration;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class GraphQlTestTemplateConfiguration {

    @Bean
    public GraphQLTestTemplate graphQLTestTemplate() {
        return new GraphQLTestTemplate();
    }

}
