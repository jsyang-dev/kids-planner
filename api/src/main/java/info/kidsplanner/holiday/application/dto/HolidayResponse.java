package info.kidsplanner.holiday.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class HolidayResponse {
    private final LocalDate date;
    private final String name;
}
