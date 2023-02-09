package com.callbus.restapi.community;

import com.callbus.restapi.domain.post.model.PostEntity;
import com.callbus.restapi.domain.post.service.PostService;
import com.callbus.restapi.domain.postLike.model.PostLikeDynamicSelect;
import com.callbus.restapi.domain.postLike.model.PostLikeEntity;
import com.callbus.restapi.domain.postLike.service.PostLikeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class PostLikeTest {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "postService")
    private PostService postService;

    @Resource(name = "postLikeService")
    private PostLikeService postLikeService;

    @Test
    public void getPostLikeList() {

        // 1. Given
        PostLikeEntity postLike = new PostLikeEntity();
        postLike.setAccount_id("Realtor 47");

        // 2. When
        List<PostLikeDynamicSelect> postLikeList = postLikeService.getPostLikeList(postLike.getAccount_id());

        // 3. Then
        Assertions.assertThat(postLikeList);

        postLikeList.stream().forEach(mapPostLike -> LOG.info("{} ::: {} ::: {}", mapPostLike.getPost_id(), mapPostLike.getTitle(), mapPostLike.getContents()) );

    }

    @Test
    public void insertPostLike() {

        // 1. Given
        PostEntity post = new PostEntity();
        post.setPost_id("11111311111313");

        PostLikeEntity postLike = new PostLikeEntity();
        postLike.setPost_id("11111311111313");
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

    @Test
    public void deletePostLike() {

        // 1. Given
        PostEntity post = new PostEntity();
        post.setPost_id("11111311111313");

        PostLikeEntity postLike = new PostLikeEntity();
        postLike.setPost_id("11111311111313");
        postLike.setAccount_id("Realtor 47");

        // 2. When
        boolean isPost = postService.isPost(post);
        boolean isPostLike = postLikeService.isPostLike(postLike.getAccount_id(), postLike.getPost_id());

        // 3. Then
        if(isPost == true && isPostLike == true) {
            postLikeService.deletePostLike(postLike);
        } else if(isPost == false) {
            LOG.info("존재하지 않는 게시글입니다.");
        }

    }

}
