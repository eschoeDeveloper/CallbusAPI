package com.callbus.restapi.domain.post.repository;

import com.callbus.restapi.domain.post.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PostRepository extends JpaRepository<PostEntity, Object>, JpaSpecificationExecutor<PostEntity> {

    @Modifying
    @Query(nativeQuery = true, value = " UPDATE CALLBUS_POST SET delete_yn = 'Y', delete_date = current_timestamp WHERE post_id = :post_id AND delete_yn = 'N' ")
    int deleteByPostId(@Param("post_id") String post_id);

}
