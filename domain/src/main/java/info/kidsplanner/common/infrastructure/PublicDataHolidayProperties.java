package info.kidsplanner.common.infrastructure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
@Validated
@ConstructorBinding
@ConfigurationProperties("app.public-data.holiday")
public class PublicDataHolidayProperties {
    @NotBlank
    private final String path;

    @NotBlank
    private final String serviceKey;
}
