package info.kidsplanner.holiday.application;

import info.kidsplanner.holiday.application.dto.HolidayRequest;
import info.kidsplanner.holiday.application.dto.HolidayResponse;
import info.kidsplanner.holiday.domain.HolidayClient;
import info.kidsplanner.holiday.domain.HolidayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class HolidayService {
    private final HolidayRepository holidayRepository;
    private final HolidayClient holidayClient;

    public Flux<HolidayResponse> createHolidays(HolidayRequest holidayRequest) {
        return holidayClient.fetchHolidays(holidayRequest.toHolidayCondition())
                .map(holidayRepository::save)
                .map(holiday -> HolidayResponse.builder()
                        .date(holiday.getDate())
                        .name(holiday.getName())
                        .build());
    }
}
