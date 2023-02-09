package com.callbus.restapi.domain.postLike.repository;

import com.callbus.restapi.domain.post.model.PostDynamicSelect;
import com.callbus.restapi.domain.postLike.model.PostLikeDynamicSelect;
import com.callbus.restapi.domain.postLike.model.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLikeEntity, Object>, JpaSpecificationExecutor<PostLikeEntity> {

    @Query(nativeQuery = true, value = "SELECT\n" +
            "\ta.*\n" +
            "FROM CALLBUS_POST a LEFT OUTER JOIN CALLBUS_POST_LIKE b\n" +
            "ON a.post_id = b.post_id\n" +
            "WHERE a.delete_yn = 'N'\n" +
            "  AND b.account_id = :account_id")
    @Transactional(readOnly = true)
    List<PostLikeDynamicSelect> findAllByDynamicQuery(@Param("account_id") String account_id);

    @Modifying
    @Query(nativeQuery = true, value = " DELETE FROM CALLBUS_POST_LIKE WHERE post_id = :post_id AND account_id = :account_id ")
    int deleteByPostIdAndAccountId(@Param("post_id") String post_id, @Param("account_id") String account_id);

}
