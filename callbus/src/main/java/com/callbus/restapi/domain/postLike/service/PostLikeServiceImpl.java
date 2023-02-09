package com.callbus.restapi.domain.postLike.service;

import com.callbus.restapi.domain.postLike.model.PostLikeEntity;
import com.callbus.restapi.domain.postLike.repository.PostLikeRepository;
import com.callbus.restapi.domain.postLike.repository.specification.PostLikeSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service("postLikeService")
public class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeRepository postLikeRepository;

    @Override
    public List<PostLikeEntity> getPostLikeList(final String accountId) {
        Specification<PostLikeEntity> specification = Specification.where( PostLikeSpecification.filterAccount(accountId) );
        return postLikeRepository.findAll(specification);
    }

    @Override
    public boolean isPostLike(final String accountId, final String postId) {
        Specification<PostLikeEntity> specification
                = Specification.where( PostLikeSpecification.filterPostLike(postId) )
                    .and( PostLikeSpecification.filterAccount(accountId) );
        return postLikeRepository.exists(specification);
    }

    @Override
    @Transactional
    public void savePostLike(PostLikeEntity postLike) {
        postLikeRepository.save(postLike);
    }

    @Override
    @Transactional
    public void deletePostLike(PostLikeEntity postLike) {
        postLikeRepository.deleteByPostIdAndAccountId(postLike.getPost_id(), postLike.getAccount_id());
    }

}
