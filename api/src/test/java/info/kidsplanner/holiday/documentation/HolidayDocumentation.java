package info.kidsplanner.holiday.documentation;

import info.kidsplanner.Documentation;
import info.kidsplanner.holiday.application.HolidayService;
import info.kidsplanner.holiday.application.dto.HolidayRequest;
import info.kidsplanner.holiday.application.dto.HolidayResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.FieldDescriptor;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

import static info.kidsplanner.holiday.acceptance.HolidayStep.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@DisplayName("공휴일 문서화")
public class HolidayDocumentation extends Documentation {
    @MockBean
    private HolidayService holidayService;

    @DisplayName("공휴일 목록을 생성한다.")
    @Test
    void createHolidays() {
        // given
        final HolidayResponse 삼일절 = HolidayResponse.builder()
                .date(LocalDate.of(2023, 3, 1))
                .name("삼일절")
                .build();
        final HolidayResponse 현충일 = HolidayResponse.builder()
                .date(LocalDate.of(2023, 6, 6))
                .name("현충일")
                .build();
        given(holidayService.createHolidays(any())).willReturn(Flux.just(삼일절, 현충일));

        final FieldDescriptor[] requestFieldDescriptors = {
                fieldWithPath("year").description("연도"),
        };
        final FieldDescriptor[] responseFieldDescriptors = {
                fieldWithPath("[].date").description("날짜"),
                fieldWithPath("[].name").description("공휴일 이름"),
        };
        final HolidayRequest holidayRequest = HolidayRequest.builder()
                .year(2023)
                .build();

        // when
        공휴일_생성(
                givenSpec(
                        "createHolidays",
                        requestFields(requestFieldDescriptors),
                        responseFields(responseFieldDescriptors)
                ),
                holidayRequest
        );
    }
}
