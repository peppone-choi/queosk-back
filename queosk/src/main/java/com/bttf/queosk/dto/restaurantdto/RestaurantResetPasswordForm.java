package com.bttf.queosk.dto.restaurantdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantResetPasswordForm {
    private String email;
    private String ownerName;
}
