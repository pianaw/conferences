package org.waveaccess.conferences.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullRoomDto {

    public Long id;

    public String code;

    public List<FullPresentationDto> presentations;
}
