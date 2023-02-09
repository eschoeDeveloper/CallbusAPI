package com.callbus.restapi.domain.postLike.service;


import com.callbus.restapi.domain.postLike.model.PostLikeEntity;

import java.util.List;

public interface PostLikeService {

    List<PostLikeEntity> getPostLikeList(String accountId);

    boolean isPostLike(String accountId, String postId);

    void savePostLike(PostLikeEntity postLike);

    void deletePostLike(PostLikeEntity postLike);

}
