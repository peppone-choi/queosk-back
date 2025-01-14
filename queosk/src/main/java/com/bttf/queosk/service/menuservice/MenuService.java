package com.bttf.queosk.service.menuservice;

import com.bttf.queosk.dto.menudto.MenuCreationForm;
import com.bttf.queosk.dto.menudto.MenuDto;
import com.bttf.queosk.dto.menudto.MenuStatusForm;
import com.bttf.queosk.dto.menudto.MenuUpdateForm;
import com.bttf.queosk.entity.Menu;
import com.bttf.queosk.exception.CustomException;
import com.bttf.queosk.mapper.menumapper.MenuDtoMapper;
import com.bttf.queosk.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.bttf.queosk.enumerate.MenuStatus.ON_SALE;
import static com.bttf.queosk.exception.ErrorCode.MENU_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    @Transactional
    public void createMenu(Long restaurantId, MenuCreationForm menuCreationForm) {
        menuRepository.save(
                Menu.builder()
                        .name(menuCreationForm.getName())
                        .price(menuCreationForm.getPrice())
                        .status(ON_SALE)
                        .imageUrl(menuCreationForm.getImageUrl())
                        .restaurantId(restaurantId)
                        .build()
        );
    }

    public List<MenuDto> getMenu(Long restaurantId) {
        List<Menu> menus = menuRepository.findByRestaurantId(restaurantId);
        if (menus.isEmpty()) {
            throw new CustomException(MENU_NOT_FOUND);
        }
        List<MenuDto> menuDtos = new ArrayList<>();
        menus.forEach(menu -> {
            menuDtos.add(MenuDtoMapper.INSTANCE.MenuToMenuDto(menu));
        });

        return menuDtos;
    }

    @Transactional
    public void updateMenuInfo(Long restaurantId, Long menuId, MenuUpdateForm menuUpdateForm) {
        Menu menu = menuRepository.findByIdAndRestaurantId(menuId, restaurantId)
                .orElseThrow(() -> new CustomException(MENU_NOT_FOUND));

        menu.setName(menuUpdateForm.getName());
        menu.setPrice(menuUpdateForm.getPrice());

        menuRepository.save(menu);
    }

    @Transactional
    public void updateMenuStatus(Long restaurantId, Long menuId, MenuStatusForm menuStatusForm) {
        Menu menu = menuRepository.findByIdAndRestaurantId(menuId, restaurantId)
                .orElseThrow(() -> new CustomException(MENU_NOT_FOUND));

        menu.setStatus(menuStatusForm.getStatus());

        menuRepository.save(menu);
    }

    @Transactional
    public void updateImage(Long restaurantId, Long menuId, String url) {

        Menu menu = menuRepository.findByIdAndRestaurantId(menuId, restaurantId)
                .orElseThrow(() -> new CustomException(MENU_NOT_FOUND));

        menu.setImageUrl(url);

        menuRepository.save(menu);
    }

    @Transactional
    public void deleteMenu(Long restaurantId, Long menuId) {
        Menu menu = menuRepository.findByIdAndRestaurantId(menuId, restaurantId)
                .orElseThrow(() -> new CustomException(MENU_NOT_FOUND));

        menuRepository.delete(menu);
    }
}
