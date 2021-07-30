package org.waveaccess.conferences.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleScheduleDto {

    public Long id;

    public String date;

    public String start;

    public String end;

    @JsonIgnore
    public Long presentationId;
}
