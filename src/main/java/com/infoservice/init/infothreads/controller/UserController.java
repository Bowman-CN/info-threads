package com.infoservice.init.infothreads.controller;

import com.infoservice.init.infothreads.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/isEmailAvailable")
    public boolean CheckEmailAvailability(@RequestParam String email) {
        if (null == userRepository.findTopByMobileOrEmail(email, email)) {
            return true;
        }

        return false;
    }

    @GetMapping("/isMobileAvailable")
    public boolean CheckMobileAvailability(@RequestParam String mobile) {
        if (null == userRepository.findTopByMobileOrEmail(mobile, mobile)) {
            return true;
        }

        return false;
    }
}
