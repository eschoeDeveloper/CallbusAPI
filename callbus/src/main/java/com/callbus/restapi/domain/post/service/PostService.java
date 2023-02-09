package com.callbus.restapi.domain.post.service;

import com.callbus.restapi.domain.post.model.PostEntity;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<PostEntity> getPostList();

    List<PostEntity> getDeletePostList(PostEntity post);

    Optional<PostEntity> getPost(PostEntity post);

    boolean isPost(PostEntity post);

    void savePost(PostEntity post);

    void deletePost(PostEntity post);

}
