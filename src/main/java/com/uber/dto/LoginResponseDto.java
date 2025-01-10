package com.uber.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class LoginResponseDto {
    // the refreshToken is saved in cookies
    private String accessToken;

}
