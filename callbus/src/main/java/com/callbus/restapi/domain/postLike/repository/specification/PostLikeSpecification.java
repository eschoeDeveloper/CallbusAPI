package com.callbus.restapi.domain.postLike.repository.specification;

import com.callbus.restapi.domain.postLike.model.PostLikeDynamicSelect;
import com.callbus.restapi.domain.postLike.model.PostLikeEntity;
import org.springframework.data.jpa.domain.Specification;

public class PostLikeSpecification {

    public static Specification<PostLikeEntity> filterAccount(final String account_id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("account_id"), account_id);
    }

    public static Specification<PostLikeEntity> filterPostLike(final String post_id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("post_id"), post_id);
    }

}
