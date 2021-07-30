package org.waveaccess.conferences.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class TokensDto {

    public String accessToken;
}
