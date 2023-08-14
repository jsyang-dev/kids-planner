package info.kidsplanner.holiday.application.dto;

import info.kidsplanner.holiday.domain.Holiday;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class HolidayResponse {
    private final LocalDate date;
    private final String name;

    public static HolidayResponse of(Holiday holiday) {
        return builder()
                .date(holiday.getDate())
                .name(holiday.getName())
                .build();
    }
}
