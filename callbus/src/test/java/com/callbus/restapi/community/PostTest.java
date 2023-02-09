package com.callbus.restapi.community;

import com.callbus.restapi.domain.post.model.PostDynamicSelect;
import com.callbus.restapi.domain.post.model.PostEntity;
import com.callbus.restapi.domain.post.service.PostService;
import com.querydsl.core.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostTest {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "postService")
    private PostService postService;

    @BeforeEach
    public void beforeEach() {
        LOG.debug("PostTest.beforeEach");
    }

    @Test
    public void listTest() {

        LOG.debug("PostTest.listTest");

        // 1. Given
        final String account_id = "Realtor 47";

        // 2. When
        List<PostDynamicSelect> getPostList = postService.getPostList(account_id);

        // 3. Then
        assertThat(getPostList);
        getPostList.stream().forEach(post -> LOG.info("{} ::: {} ::: {}", post.getPost_id(), post.getTitle(), post.getContents()));

        LOG.debug("PostTest.listTest End");

    }

    @Test
    public void insertTest() {

        LOG.debug("PostTest.insertTest");

        // 1. Given

        PostEntity post = new PostEntity();

        post.setPost_id("CP002");
        post.setTitle("등록글 테스트3");
        post.setContents("등록글 테스트3입니다. 안녕하세요. 잘 부탁드립니다.");
        // 삭제여부는 쿼리에서 기본값으로 자동 입력되어 set을 하지 않았습니다.
        // 생성일자는 쿼리에서 자동 입력되어 set을 하지 않았습니다.

        // 2. When
        Optional<PostEntity> getPost = postService.getPost(post);

        if(getPost.isPresent()) {

            PostEntity presentPost = getPost.get();

            if(presentPost.getDelete_yn().matches("N")) {
                postService.savePost(post);
            }

        }

        // 3. Then
        LOG.debug("PostTest.insertTest End");

    }

    @Test
    public void updateTest() {

        // 1. Given
        PostEntity post = new PostEntity();

        post.setId(4);
        post.setPost_id("CP002");
        post.setTitle("등록글 테스트313131111");
        post.setContents("등록글 테스트31311311입니다. 안녕하세요. 잘 부탁드립니다.");
        post.setUpdate_date( LocalDateTime.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") ) );

        // 2. When
        Optional<PostEntity> getPost = postService.getPost(post);

        if(!getPost.isPresent()) {
            postService.savePost(post);
        } else {
            LOG.info("이미 등록된 게시글입니다.");
        }

        // 3. Then
        LOG.debug("PostTest.insertTest End");

    }

    @Test
    public void deleteTest() {

        LOG.debug("PostTest.deleteTest");

        // 1. Given
        PostEntity post = new PostEntity();
        post.setPost_id("CP002");

        // 2. When
        postService.deletePost(post);

        // 3. Then
        LOG.debug("PostTest.deleteTest End");

    }

    @AfterEach
    public void afterEach() {
        LOG.debug("PostTest.afterEach");
    }

}
