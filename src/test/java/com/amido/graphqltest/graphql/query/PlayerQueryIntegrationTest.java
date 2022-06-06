package com.amido.graphqltest.graphql.query;

import com.amido.graphqltest.GraphqlTestApplication;
import com.amido.graphqltest.configuration.GraphQlIntegrationTestBase;
import com.graphql.spring.boot.test.GraphQLResponse;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = GraphqlTestApplication.class
)
class PlayerQueryIntegrationTest extends GraphQlIntegrationTestBase {

    @Test
    public void givenAllPlayersAreRequested_WhenGraphQlQueryIsMade_ThenJsonContainingThreePlayersIsReturned() throws IOException, JSONException {
        final GraphQLResponse result = graphQLTestTemplate.postForResource(String.format(GRAPHQL_REQUEST_RESOURCE, QUERY, "allPlayers"));

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        final String expectedResponse = readFile(String.format(GRAPHQL_RESPONSE_RESOURCE, QUERY, "allPlayers"));

        assertEquals(expectedResponse, result.getRawResponse().getBody(), true);
    }
}