package com.infoservice.init.infothreads.controller;

import com.infoservice.init.infothreads.models.UserMetrics;
import com.infoservice.init.infothreads.payload.APIResponse;
import com.infoservice.init.infothreads.payload.JwtAuthResponse;
import com.infoservice.init.infothreads.repos.UserRepository;
import com.infoservice.init.infothreads.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserMetrics userLogin) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.email,
                        userLogin.password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthResponse(jwt));
    }

    @PostMapping("/new")
    public ResponseEntity<?> registerUser(@RequestBody UserMetrics newUser) {
        if(null != userRepository.findTopByMobileOrEmail(newUser.mobile,newUser.mobile)) {
            return new ResponseEntity(new APIResponse(false, "This mobile is already registered!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(null != userRepository.findTopByMobileOrEmail(newUser.email,newUser.email)) {
            return new ResponseEntity(new APIResponse(false, "This email address is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        UserMetrics user = new UserMetrics(null,newUser.name, newUser.email, newUser.mobile,
                newUser.avatar,newUser.password, newUser.roles);

        user.password = passwordEncoder.encode(user.password);

//        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
//                .orElseThrow(() -> new AppException("User Role not set."));
//
//        user.setRoles(Collections.singleton(userRole));

        UserMetrics result = userRepository.insert(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.name).toUri();

        return ResponseEntity.created(location).body(new APIResponse(true, "User registered successfully"));
    }
}
