package com.amido.graphqltest.graphql.mutation;

import com.amido.graphqltest.GraphqlTestApplication;
import com.amido.graphqltest.configuration.GraphQlIntegrationTestBase;
import com.graphql.spring.boot.test.GraphQLResponse;
import org.json.JSONException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = GraphqlTestApplication.class
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlayerMutationIT extends GraphQlIntegrationTestBase {

    @AfterAll
    void cleanUp() throws IOException {
        graphQLTestTemplate.postForResource(String.format(GRAPHQL_REQUEST_RESOURCE, MUTATION, "removePlayer"));
    }

    @Test
    public void givenAPlayerShouldBeAdded_WhenAddingPlayer_ThenJsonContainingTheCreatedPlayerIsReturned() throws IOException, JSONException {
        final GraphQLResponse result = graphQLTestTemplate.postForResource(String.format(GRAPHQL_REQUEST_RESOURCE, MUTATION, "addPlayer"));

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        final String expectedResponse = readFile(String.format(GRAPHQL_RESPONSE_RESOURCE, MUTATION, "addPlayer"));

        assertEquals(expectedResponse, result.getRawResponse().getBody(), true);
    }

}