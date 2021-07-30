package org.waveaccess.conferences.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFormDto {

    public String name;

    public String email;

    public String password;


}
