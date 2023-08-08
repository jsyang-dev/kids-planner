package info.kidsplanner.configuration;

import info.kidsplanner.common.infrastructure.ClientHttpConnectorFactory;
import info.kidsplanner.common.infrastructure.PublicDataConnectionProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.util.retry.Retry;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class WebClientConfiguration {
    private final PublicDataConnectionProperties publicDataConnectionProperties;

    @Bean
    public WebClient publicDataWebClient() {
        return WebClient.builder()
                .uriBuilderFactory(createDefaultUriBuilderFactory())
                .clientConnector(ClientHttpConnectorFactory.from(publicDataConnectionProperties))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public Retry publicDataRetry() {
        return Retry.backoff(
                        publicDataConnectionProperties.getRetryMaxAttempts(),
                        Duration.ofMillis(publicDataConnectionProperties.getRetryMaxDelay())
                )
                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> retrySignal.failure());
    }

    private DefaultUriBuilderFactory createDefaultUriBuilderFactory() {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(publicDataConnectionProperties.getDomain());
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        return factory;
    }
}
