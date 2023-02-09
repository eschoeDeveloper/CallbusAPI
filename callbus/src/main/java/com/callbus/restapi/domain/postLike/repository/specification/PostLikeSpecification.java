package com.callbus.restapi.domain.postLike.repository.specification;

import com.callbus.restapi.domain.postLike.model.PostLikeEntity;
import org.springframework.data.jpa.domain.Specification;

public class PostLikeSpecification {

    public static Specification<PostLikeEntity> filterAccount(final String accountId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("account_id"), accountId);
    }

    public static Specification<PostLikeEntity> filterPostLike(final String postId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("post_id"), postId);
    }

}
