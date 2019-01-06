package com.infoservice.init.infothreads.security;

import com.infoservice.init.infothreads.models.UserMetrics;
import com.infoservice.init.infothreads.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserPrincipal loadUserByUsername(String mobileOrMail) throws UsernameNotFoundException {
        UserMetrics userMetrics = userRepository.findTopByMobileOrEmail(mobileOrMail, mobileOrMail);
        if (userMetrics == null) {
            throw new UsernameNotFoundException("User not found with username(email) -" + mobileOrMail);
        }
        return UserPrincipal.create(userMetrics);
    }

    @Transactional
    public UserPrincipal loadUserById(String id) {
        UserMetrics userMetrics = userRepository.findTopBy_id(id);
        if (userMetrics == null) {
            throw new UsernameNotFoundException("User not found with UserMetrics _id -" + id);
        }
        return UserPrincipal.create(userMetrics);
    }
}
