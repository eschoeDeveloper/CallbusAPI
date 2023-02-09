package com.callbus.restapi.domain.user.repository;

import com.callbus.restapi.domain.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<UserEntity, Object>, JpaSpecificationExecutor<UserEntity> {

}
