package com.callbus.restapi.domain.post.service;

import com.callbus.restapi.domain.post.model.PostEntity;
import com.callbus.restapi.domain.post.repository.PostRepository;
import com.callbus.restapi.domain.post.repository.specification.PostSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service("postService")
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<PostEntity> getPostList() {
        return postRepository.findAll();
    }

    @Override
    public List<PostEntity> getDeletePostList(PostEntity post) {
        Specification<PostEntity> spec = Specification.where( PostSpecification.findDeletePost() );
        return postRepository.findAll(spec);
    }

    @Override
    public Optional<PostEntity> getPost(final PostEntity post) {
        Specification<PostEntity> spec = Specification.where(PostSpecification.findPost(post.getPost_id()));
        return postRepository.findOne(spec);
    }

    @Override
    public boolean isPost(PostEntity post) {
        Specification<PostEntity> spec = Specification.where(PostSpecification.findPost(post.getPost_id()));
        return postRepository.exists(spec);
    }

    @Override
    @Transactional
    public void savePost(PostEntity post) {
        postRepository.save(post);
    }

    @Override
    @Transactional
    public void deletePost(PostEntity post) {
        postRepository.deleteByPostId(post.getPost_id());
    }

}
