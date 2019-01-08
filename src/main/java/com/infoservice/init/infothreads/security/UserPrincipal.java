package com.infoservice.init.infothreads.security;

import com.infoservice.init.infothreads.models.UserMetrics;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
    private UserMetrics userMetrics;
    private Collection<? extends GrantedAuthority> authorities;

    public String getId() {
        return this.userMetrics.get_id();
    }

    public String getName() {
        return this.userMetrics.name;
    }

    public String getMobile() {
        return this.userMetrics.mobile;
    }

    public String getAvatar() {
        return this.userMetrics.avatar;
    }

    public String getEmail() {
        return this.userMetrics.email;
    }

    public UserPrincipal(UserMetrics user, Collection<? extends GrantedAuthority> authorities) {
        this.userMetrics = user;
        this.authorities = authorities;
    }

    public static UserPrincipal create(UserMetrics user) {
        List<GrantedAuthority> authorities = user.roles.stream().map(role ->
                new SimpleGrantedAuthority(role.name)
        ).collect(Collectors.toList());

        return new UserPrincipal(
                user,
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.userMetrics.password;
    }

    @Override
    public String getUsername() {
        return this.userMetrics.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(userMetrics._id, that.userMetrics._id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userMetrics.get_id());
    }
}
