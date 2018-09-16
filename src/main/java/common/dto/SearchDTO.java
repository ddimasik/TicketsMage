package common.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//TODO    SearchDTO лучше переименовать, чтобы было понятно, что это В САМОМ ДЕЛЕ параметры поиска поезда

public class SearchDTO {

    private int startStationId;

    private int endStationId;

    private Object startDateTime;

    private Object endDateTime;

    @Override
    public String toString() {
        return "SearchDTO{" +
                "startStationId=" + startStationId +
                ", startDateTime=" + startDateTime +
                ", endStationId=" + endStationId +
                ", endDateTime=" + endDateTime +
                '}';
    }
}
