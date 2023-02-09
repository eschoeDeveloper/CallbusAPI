package com.callbus.restapi.domain.post.service;

import com.callbus.restapi.domain.post.model.PostDynamicSelect;
import com.callbus.restapi.domain.post.model.PostEntity;
import com.querydsl.core.Tuple;

import java.util.List;
import java.util.Optional;

public interface PostService {

//    List<PostEntity> getPostList(String account_id);

    List<PostDynamicSelect> getPostList(String account_id);

    List<PostEntity> getDeletePostList();

    Optional<PostEntity> getPost(PostEntity post);

    boolean isPost(PostEntity post);

    void savePost(PostEntity post);

    void deletePost(PostEntity post);

}
