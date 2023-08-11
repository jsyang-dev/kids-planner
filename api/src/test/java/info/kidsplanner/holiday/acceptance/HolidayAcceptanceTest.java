package info.kidsplanner.holiday.acceptance;

import info.kidsplanner.AcceptanceTest;
import info.kidsplanner.holiday.application.dto.HolidayRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static info.kidsplanner.holiday.acceptance.HolidayStep.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("공휴일 인수 테스트")
public class HolidayAcceptanceTest extends AcceptanceTest {
    @DisplayName("공휴일 목록을 생성한다.")
    @Test
    void createHolidays() {
        // given
        final HolidayRequest holidayRequest = createHolidayRequest();

        // when
        final ExtractableResponse<Response> response = 공휴일_생성(holidayRequest);

        // then
        공휴일_생성됨(response);
    }

    private void 공휴일_생성됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private HolidayRequest createHolidayRequest() {
        return HolidayRequest.builder()
                .year(2023)
                .build();
    }
}
