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
public class FullPresentationDto {

    public Long id;

    public String name;

    @JsonProperty(value = "schedule")
    public SimpleScheduleDto simpleScheduleDto;


}
