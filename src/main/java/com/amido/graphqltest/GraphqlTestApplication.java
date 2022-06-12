package com.amido.graphqltest;

import com.amido.graphqltest.properties.ElasticsearchProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(value = {
        ElasticsearchProperties.class
})
public class GraphqlTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlTestApplication.class, args);
    }

}
