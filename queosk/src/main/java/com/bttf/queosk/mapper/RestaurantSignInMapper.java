package com.bttf.queosk.mapper;

import com.bttf.queosk.dto.restaurantdto.RestaurantSignInDto;
import com.bttf.queosk.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy =
        ReportingPolicy.IGNORE)
public interface RestaurantSignInMapper extends EntityMapper<RestaurantSignInDto, Restaurant> {

    RestaurantSignInMapper MAPPER =
            Mappers.getMapper(RestaurantSignInMapper.class);


    @Mapping(source = "restaurant.ownerId", target = "ownerId")
    @Mapping(source = "restaurant.restaurantName", target = "restaurantName")
    @Mapping(source = "restaurant.imageUrl", target = "imageUrl")
    RestaurantSignInDto toDto(Restaurant restaurant, String refreshToken, String accessToken);

}
