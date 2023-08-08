package info.kidsplanner.holiday.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HolidayResult {
    private static final DateTimeFormatter DATE_FORMAT_YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
    private Response response;

    public String getResultCode() {
        return response.getHeader().getResultCode();
    }

    public String getResultMsg() {
        return response.getHeader().getResultMsg();
    }

    public List<Holiday> toHolidays() {
        return response.getBody().getItems().getItem().stream()
                .map(item -> Holiday.builder()
                        .date(LocalDate.parse(item.getLocdate(), DATE_FORMAT_YYYYMMDD))
                        .name(item.getDateName())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Getter
    public static class Response {
        private Header header;
        private Body body;
    }

    @Getter
    public static class Header {
        // 결과코드
        private String resultCode;

        // 결과메시지
        private String resultMsg;
    }

    @Getter
    public static class Body {
        // 결과
        private Items items;

        // 페이지당항목수
        private int numOfRows;

        // 페이지
        private int pageNo;

        // 모든항목수
        private int totalCount;
    }

    @Getter
    public static class Items {
        @JsonProperty("item")
        private List<Item> item;
    }

    @Getter
    public static class Item {
        // 종류
        private String dateKind;

        // 명칭
        private String dateName;

        // 공공기관 휴일여부
        private String isHoliday;

        // 날짜
        private String locdate;

        // 순번
        private int seq;
    }
}
