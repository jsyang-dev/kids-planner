package info.kidsplanner.holiday.ui;

import info.kidsplanner.holiday.application.HolidayService;
import info.kidsplanner.holiday.application.dto.HolidayRequest;
import info.kidsplanner.holiday.application.dto.HolidayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

@RestController
@RequestMapping("/holidays")
@RequiredArgsConstructor
public class HolidayController {
    private final HolidayService holidayService;

    @PostMapping
    public ResponseEntity<Flux<HolidayResponse>> createHolidays(@RequestBody @Valid HolidayRequest holidayRequest) {
        return ResponseEntity.ok(holidayService.createHolidays(holidayRequest));
    }
}
