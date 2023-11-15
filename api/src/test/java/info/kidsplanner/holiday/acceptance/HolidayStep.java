package info.kidsplanner.holiday.acceptance;

import info.kidsplanner.AcceptanceStep;
import info.kidsplanner.holiday.application.dto.HolidayRequest;
import info.kidsplanner.util.AcceptanceUtil;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HolidayStep extends AcceptanceStep {
    public static final String HOLIDAY_URI = "/holidays";

    public static ExtractableResponse<Response> 공휴일_목록_생성(HolidayRequest holidayRequest) {
        return 공휴일_목록_생성(givenSpec(), holidayRequest);
    }

    public static ExtractableResponse<Response> 공휴일_목록_생성(RequestSpecification specification, HolidayRequest holidayRequest) {
        return AcceptanceUtil.invokeHttpPost(specification, HOLIDAY_URI, holidayRequest);
    }
}
