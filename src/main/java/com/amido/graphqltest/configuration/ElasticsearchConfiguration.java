package com.amido.graphqltest.configuration;

import com.amido.graphqltest.properties.ElasticsearchProperties;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.amido.graphqltest.repository")
@RequiredArgsConstructor
public class ElasticsearchConfiguration {

    private final ElasticsearchProperties elasticsearchProperties;

    @Bean
    public RestHighLevelClient client() {
        final ClientConfiguration clientConfiguration
                = ClientConfiguration.builder()
                .connectedTo(String.join(":", elasticsearchProperties.getHost()), elasticsearchProperties.getPort())
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }
}
