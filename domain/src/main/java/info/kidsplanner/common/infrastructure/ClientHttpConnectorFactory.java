package info.kidsplanner.common.infrastructure;

import io.netty.channel.ChannelOption;
import io.netty.handler.logging.LogLevel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import java.time.Duration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientHttpConnectorFactory {
    public static ReactorClientHttpConnector from(ConnectionProperties connectionProperties) {
        return new ReactorClientHttpConnector(createHttpClient(connectionProperties));
    }

    /**
     * Connection Timeout과 Response Timeout 설정을 위한 HttpClient 생성
     */
    private static HttpClient createHttpClient(ConnectionProperties connectionProperties) {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionProperties.getConnectionTimeout())
                .responseTimeout(Duration.ofMillis(connectionProperties.getResponseTimeout()))
                .wiretap("reactor.netty.http.client.HttpClient", LogLevel.INFO, AdvancedByteBufFormat.TEXTUAL);
    }
}
