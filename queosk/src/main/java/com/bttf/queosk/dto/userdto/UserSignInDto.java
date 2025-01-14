package com.bttf.queosk.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignInDto {
    private Long id;
    private String accessToken;
    private String refreshToken;
    private String email;
    private String nickname;
    private String imageUrl;
    private String loginType;
}
