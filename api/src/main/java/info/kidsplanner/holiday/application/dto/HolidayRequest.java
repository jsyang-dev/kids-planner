package info.kidsplanner.holiday.application.dto;

import info.kidsplanner.holiday.domain.HolidayCondition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HolidayRequest {
    @NotNull
    private int year;

    public HolidayCondition toHolidayCondition() {
        return HolidayCondition.builder()
                .year(year)
                .build();
    }
}
