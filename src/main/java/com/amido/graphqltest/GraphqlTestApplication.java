package com.amido.graphqltest;

import com.amido.graphqltest.config.GraphQlConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(GraphQlConfiguration.class)
@SpringBootApplication
public class GraphqlTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlTestApplication.class, args);
    }

}
