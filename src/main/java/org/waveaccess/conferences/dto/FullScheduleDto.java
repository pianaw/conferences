package org.waveaccess.conferences.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullScheduleDto {

    public Long id;

    @JsonProperty(value = "presentation")
    public SimplePresentationDto simplePresentationDto;

    @JsonProperty(value = "room")
    public SimpleRoomDto simpleRoomDto;

    public String date;

    public String start;

    public String end;
}
