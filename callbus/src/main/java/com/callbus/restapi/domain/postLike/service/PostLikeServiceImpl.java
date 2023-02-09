package com.callbus.restapi.domain.postLike.service;

import com.callbus.restapi.domain.postLike.model.PostLikeDynamicSelect;
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
    public List<PostLikeDynamicSelect> getPostLikeList(final String account_id) {
        return postLikeRepository.findAllByDynamicQuery(account_id);
    }

    @Override
    public boolean isPostLike(final String account_id, final String post_id) {
        Specification<PostLikeEntity> specification
                = Specification.where( PostLikeSpecification.filterPostLike(post_id) )
                    .and( PostLikeSpecification.filterAccount(account_id) );
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
