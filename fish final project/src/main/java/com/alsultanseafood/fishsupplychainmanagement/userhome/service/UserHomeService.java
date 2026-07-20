package com.alsultanseafood.fishsupplychainmanagement.userhome.service;



import com.alsultanseafood.fishsupplychainmanagement.userhome.dto.UserHomeDto;

public interface UserHomeService {

    UserHomeDto getHomeData(
        Long userId);
}
