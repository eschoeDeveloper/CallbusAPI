package com.callbus.restapi.core.security;

import com.callbus.restapi.domain.user.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
@Getter
public class UserDetailsModel implements UserDetails {

    private int id;

    private String account_id;

    private String account_type;

    private String nickname;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsModel build(UserEntity user) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getAccount_type()));

        return new UserDetailsModel(
          user.getId(),
          user.getAccount_id(),
          user.getAccount_type(),
          user.getNickname(),
          authorities
        );

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        return Objects.equals(id, ((UserDetailsModel) obj).id);

    }
}
