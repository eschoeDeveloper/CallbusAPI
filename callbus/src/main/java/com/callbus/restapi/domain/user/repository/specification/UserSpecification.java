package com.callbus.restapi.domain.user.repository.specification;

import com.callbus.restapi.domain.user.model.UserEntity;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<UserEntity> findUser(String account_id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("account_id"), account_id);
    }

}
