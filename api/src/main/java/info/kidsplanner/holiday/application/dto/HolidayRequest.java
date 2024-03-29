package info.kidsplanner.holiday.application.dto;

import info.kidsplanner.holiday.domain.HolidayCondition;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HolidayRequest {
    @Positive
    private int year;

    public HolidayCondition toHolidayCondition() {
        return HolidayCondition.builder()
                .year(year)
                .build();
    }
}
