package com.alsultanseafood.fishsupplychainmanagement.userhome.controller;



import com.alsultanseafood.fishsupplychainmanagement.userhome.dto.UserHomeDto;
import com.alsultanseafood.fishsupplychainmanagement.userhome.service.UserHomeService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-home")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserHomeController {

    private final UserHomeService
            userHomeService;

    public UserHomeController(
            UserHomeService userHomeService) {

        this.userHomeService =
                userHomeService;
    }

    @GetMapping("/{userId}")
public UserHomeDto getHomeData(
        @PathVariable
        Long userId){

    return userHomeService
            .getHomeData(
                    userId);
}
}
