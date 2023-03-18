package demo.ecommerce.apis.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "demo.ecommerce.apis.elasticSearchRepo")
@ComponentScan(basePackages = "demo.ecommerce.apis")
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {
    @Value("${elasticsearch_url}")
    private String elasticsearch;

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(elasticsearch)
                .build();

        return RestClients.create(clientConfiguration)
                .rest();
    }
}
