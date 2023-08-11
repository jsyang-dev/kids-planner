package info.kidsplanner.common.infrastructure;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@RequiredArgsConstructor
@Validated
@ConstructorBinding
@ConfigurationProperties("app.public-data.connection")
public class PublicDataConnectionProperties implements ConnectionProperties {
    @NotBlank
    private final String domain;

    @NotNull
    @Positive
    private final Integer connectionTimeout;

    @NotNull
    @Positive
    private final Integer responseTimeout;

    @NotNull
    @PositiveOrZero
    private final Integer retryMaxAttempts;

    @NotNull
    @Positive
    private final Integer retryMaxDelay;
}
