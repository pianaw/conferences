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
public class SimplePresentationDto {

    public Long id;

    public String name;

    @JsonIgnore
    public Long newUser;

}
