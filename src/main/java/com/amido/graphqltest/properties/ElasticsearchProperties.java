package com.amido.graphqltest.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.elasticsearch")
public class ElasticsearchProperties {
    private String host;
    private String port;
}
