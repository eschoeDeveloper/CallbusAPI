package com.callbus.restapi.domain.post.repository;

import com.callbus.restapi.domain.post.model.PostDynamicSelect;
import com.callbus.restapi.domain.post.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Object>, JpaSpecificationExecutor<PostEntity> {

    @Query(nativeQuery = true, value = " SELECT id FROM CALLBUS_POST WHERE post_id = :post_id ")
    int getPostUniqueId(@Param("post_id") String post_id);

    @Query(nativeQuery = true, value = "SELECT\n" +
            "\t a.*\n" +
            "  , (\n" +
            "\tCASE\n" +
            "\t\tWHEN ( SELECT COUNT(account_id) FROM CALLBUS_POST_LIKE WHERE b.post_id = a.post_id and account_id = :account_id ) > 0  THEN 'Y'\n" +
            "\t\tELSE 'N'\n" +
            "\tEND\n" +
            "  ) AS like_yn\n" +
            "  , COUNT(b.account_id) AS like_count \n" +
            "FROM CALLBUS_POST a LEFT OUTER JOIN CALLBUS_POST_LIKE b\n" +
            "ON a.post_id = b.post_id\n" +
            "WHERE a.delete_yn = 'N'\n")
    @Transactional(readOnly = true)
    List<PostDynamicSelect> findAllByDynamicQuery(@Param("account_id") String account_id);

    @Modifying
    @Query(nativeQuery = true, value = " UPDATE CALLBUS_POST SET delete_yn = 'Y', delete_date = current_timestamp WHERE post_id = :post_id AND delete_yn = 'N' ")
    int deleteByPostId(@Param("post_id") String post_id);

}
