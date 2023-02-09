package com.callbus.restapi.community;

import com.callbus.restapi.domain.post.model.PostEntity;
import com.callbus.restapi.domain.post.service.PostService;
import com.callbus.restapi.domain.postLike.model.PostLikeEntity;
import com.callbus.restapi.domain.postLike.service.PostLikeService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class PostLikeTest {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "postService")
    private PostService postService;

    @Resource(name = "postLikeService")
    private PostLikeService postLikeService;

    @Test
    public void postLikeTest() {

        // 1. Given
        PostEntity post = new PostEntity();
        post.setPost_id("CP002");

        PostLikeEntity postLike = new PostLikeEntity();
        postLike.setPost_id("CP002");
        postLike.setAccount_id("Realtor 47");

        // 2. When
        boolean isPost = postService.isPost(post);
        boolean isPostLike = postLikeService.isPostLike(postLike.getAccount_id(), postLike.getPost_id());

        // 3. Then
        if(isPost == true && isPostLike == false) {
            postLikeService.savePostLike(postLike);
        } else if(isPost == false) {
            LOG.info("존재하지 않는 게시글입니다.");
        } else if(isPostLike == true) {
            LOG.info("이미 추천한 게시글입니다.");
        }

    }

}
