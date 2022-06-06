package com.amido.graphqltest.configuration;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;

public abstract class GraphQlIntegrationTestBase {
    protected final static String QUERY = "query";
    protected final static String MUTATION = "mutation";

    protected final static String GRAPHQL_REQUEST_RESOURCE = "graphql/request/%s/%s.graphql";
    protected final static String GRAPHQL_RESPONSE_RESOURCE = "graphql/response/%s/%s.json";

    @Autowired
    protected GraphQLTestTemplate graphQLTestTemplate;

    protected String readFile(final String filename) throws IOException {
        return Files.readString(new ClassPathResource(filename).getFile().toPath());
    }
}
