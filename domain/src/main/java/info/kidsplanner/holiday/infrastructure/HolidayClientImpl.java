package info.kidsplanner.holiday.infrastructure;

import info.kidsplanner.common.domain.exception.ApiErrorException;
import info.kidsplanner.common.infrastructure.PublicDataHolidayProperties;
import info.kidsplanner.holiday.domain.Holiday;
import info.kidsplanner.holiday.domain.HolidayClient;
import info.kidsplanner.holiday.domain.HolidayCondition;
import info.kidsplanner.holiday.domain.HolidayResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class HolidayClientImpl implements HolidayClient {
    private static final String RESULT_TYPE = "json";
    private static final String SUCCESS_CODE = "00";

    private final WebClient publicDataWebClient;
    private final Retry publicDataRetry;
    private final PublicDataHolidayProperties publicDataHolidayProperties;

    @Override
    public Flux<Holiday> fetchHolidays(HolidayCondition holidayCondition) {
        return publicDataWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(publicDataHolidayProperties.getPath())
                        .queryParam("solYear", holidayCondition.getYear())
                        .queryParam("ServiceKey", publicDataHolidayProperties.getServiceKey())
                        .queryParam("_type", RESULT_TYPE)
                        .build()
                )
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> clientResponse
                        .createException()
                        .map(e -> new ApiErrorException(String.format("HTTP 에러 코드 [%s]", clientResponse.statusCode())))) // 테스트 해보기!!
                .bodyToMono(HolidayResult.class)
                .filter(holidayResult -> Objects.equals(holidayResult.getResultCode(), SUCCESS_CODE))
                .flatMapIterable(HolidayResult::toHolidays)
                .switchIfEmpty(Mono.error(new ApiErrorException("[resultCode]가 [01]이 아님")))
                .onErrorResume(throwable -> {
                    log.error("공휴일 조회 API 호출 실패: {}", throwable.getMessage(), throwable);
                    return Mono.error(new ApiErrorException(throwable.getMessage()));
                })
                .retryWhen(publicDataRetry);
    }
}
