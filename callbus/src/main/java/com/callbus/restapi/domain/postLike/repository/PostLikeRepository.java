package com.callbus.restapi.domain.postLike.repository;

import com.callbus.restapi.domain.postLike.model.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostLikeRepository extends JpaRepository<PostLikeEntity, Object>, JpaSpecificationExecutor<PostLikeEntity> {

    @Modifying
    @Query(nativeQuery = true, value = " DELETE FROM CALLBUS_POST_LIKE WHERE post_id = :post_id AND account_id = :account_id ")
    int deleteByPostIdAndAccountId(@Param("post_id") String post_id, @Param("account_id") String account_id);

}
