package com.callbus.restapi.domain.post.repository.specification;

import com.callbus.restapi.domain.post.model.PostEntity;
import org.springframework.data.jpa.domain.Specification;

public class PostSpecification {

    public static Specification<PostEntity> findPost(final String postId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("post_id"), postId);
    }

    public static Specification<PostEntity> findDeletePost() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("delete_yn"), "Y");
    }

}
