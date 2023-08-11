package info.kidsplanner.holiday.domain;

import reactor.core.publisher.Flux;

public interface HolidayClient {
    Flux<Holiday> fetchHolidays(HolidayCondition holidayCondition);
}
