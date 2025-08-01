package com.callbus.restapi.domain.postLike.controller;


import com.callbus.restapi.core.model.ResponseApi;
import com.callbus.restapi.core.security.UserDetailsModel;
import com.callbus.restapi.domain.post.model.PostEntity;
import com.callbus.restapi.domain.postLike.model.PostLikeDynamicSelect;
import com.callbus.restapi.domain.postLike.model.PostLikeEntity;
import com.callbus.restapi.domain.postLike.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/callbus/community/post/like")
public class PostLikeController {

    private final PostLikeService postLikeService;

    /**
     * 커뮤니티 글 좋아요 목록
     * @param userDetails
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<ResponseApi> getPostLikeList(
            @AuthenticationPrincipal UserDetailsModel userDetails
    ) {

        final ResponseApi responseApi = new ResponseApi();

        try {

            List<PostLikeDynamicSelect> postLikeList = postLikeService.getPostLikeList(userDetails.getAccount_id());

            responseApi.setResponseCode(HttpStatus.OK.value());
            responseApi.setResponseData(postLikeList);
            responseApi.setResponseMessage(HttpStatus.OK.name());

        } catch(Exception e) {

            responseApi.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseApi.setResponseData(new Object());
            responseApi.setResponseMessage(e.getMessage());

        }

        return null;

    }


    /**
     * 커뮤니티 글 좋아요 설정
     * @param postLike
     * @param userDetails
     * @return
     */
    @PostMapping("/insert")
    public ResponseEntity<ResponseApi> insertPostLike(
            @RequestBody PostLikeEntity postLike,
            @AuthenticationPrincipal UserDetailsModel userDetails
    ) {

        final ResponseApi responseApi = new ResponseApi();

        try {

            boolean isPostLike = postLikeService.isPostLike(userDetails.getAccount_id(), postLike.getPost_id());

            if(isPostLike == false) {

                postLike.setAccount_id(userDetails.getAccount_id());

                postLikeService.savePostLike(postLike);

                responseApi.setResponseCode(HttpStatus.OK.value());
                responseApi.setResponseData("좋아요 완료");
                responseApi.setResponseMessage(HttpStatus.OK.name());

            } else {

                responseApi.setResponseCode(HttpStatus.FOUND.value());
                responseApi.setResponseData("이미 좋아요를 체크한 게시물입니다.");
                responseApi.setResponseMessage(HttpStatus.FOUND.name());

            }

        } catch(Exception e) {

            responseApi.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseApi.setResponseData("오류");
            responseApi.setResponseMessage(e.getMessage());

        }

        return ResponseEntity.ok(responseApi);

    }

    /**
     * 커뮤니티 글 좋아요 취소
     * @param postLike
     * @param userDetails
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseApi> deletePostLike(
            @RequestBody PostLikeEntity postLike,
            @AuthenticationPrincipal UserDetailsModel userDetails
    ) {

        final ResponseApi responseApi = new ResponseApi();

        try {

            postLike.setAccount_id(userDetails.getAccount_id());

            postLikeService.deletePostLike(postLike);

            responseApi.setResponseCode(HttpStatus.OK.value());
            responseApi.setResponseData("좋아요 취소 완료");
            responseApi.setResponseMessage(HttpStatus.OK.name());

        } catch(Exception e) {

            responseApi.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseApi.setResponseData("오류");
            responseApi.setResponseMessage(e.getMessage());

        }

        return ResponseEntity.ok(responseApi);

    }

}
