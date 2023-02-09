package com.callbus.restapi.core.security;

import com.callbus.restapi.domain.user.model.UserEntity;
import com.callbus.restapi.domain.user.repository.UserRepository;
import com.callbus.restapi.domain.user.repository.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String account_id) throws UsernameNotFoundException {

        Specification spec = Specification.where(UserSpecification.findUser(account_id));

        Optional<UserEntity> findUser = userRepository.findOne(spec);

        UserEntity user;

        if(!findUser.isPresent()) {

            user = new UserEntity();

            user.setId(0);
            user.setNickname("외부사용자");
            user.setQuit("N");
            user.setAccount_id("Guest");
            user.setAccount_type("GUEST");

        } else {
            user = findUser.get();
        }

        return UserDetailsModel.build(Optional.of(user).get());

    }

}
