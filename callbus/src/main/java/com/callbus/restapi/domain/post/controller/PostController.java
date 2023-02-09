package com.callbus.restapi.domain.post.controller;

import com.callbus.restapi.core.model.ResponseApi;
import com.callbus.restapi.core.security.UserDetailsModel;
import com.callbus.restapi.domain.post.model.PostEntity;
import com.callbus.restapi.domain.post.service.PostService;
import com.callbus.restapi.domain.user.model.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/callbus/community/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/list")
    public ResponseEntity<ResponseApi> getPostList(

    ) {

        ResponseApi responseApi = new ResponseApi();

        try {

            List<PostEntity> postList = postService.getPostList();

            responseApi.setResponseCode(HttpStatus.OK.value());
            responseApi.setResponseData(postList);
            responseApi.setResponseMessage(HttpStatus.OK.name());

        } catch(Exception e) {

            responseApi.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseApi.setResponseData(new Object());
            responseApi.setResponseMessage(e.getMessage());

        }

        return ResponseEntity.ok(responseApi);

    }

    @GetMapping("/get")
    public ResponseEntity<ResponseApi> getPost(
            @RequestBody PostEntity post
    ) {

        ResponseApi responseApi = new ResponseApi();

        try {

            Optional<PostEntity> getPost = postService.getPost(post);

            responseApi.setResponseCode(HttpStatus.OK.value());
            responseApi.setResponseData(getPost.get());
            responseApi.setResponseMessage(HttpStatus.OK.name());

        } catch(Exception e) {

            responseApi.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseApi.setResponseData(new Object());
            responseApi.setResponseMessage(e.getMessage());

        }

        return ResponseEntity.ok(responseApi);

    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseApi> insertPost(
            @RequestBody PostEntity post,
            @AuthenticationPrincipal UserDetailsModel userDetailsModel
    ) {

        ResponseApi responseApi = new ResponseApi();

        try {

            if(userDetailsModel == null && userDetailsModel.getAccount_type().matches("GUEST")) {

                responseApi.setResponseCode(HttpStatus.UNAUTHORIZED.value());
                responseApi.setResponseData("FAIL");
                responseApi.setResponseMessage("외부 사용자는 글 작성을 허용하지 않습니다.");

            } else {

                postService.savePost(post);

                responseApi.setResponseCode(HttpStatus.OK.value());
                responseApi.setResponseData("등록 완료");
                responseApi.setResponseMessage(HttpStatus.OK.name());

            }

        } catch(Exception e) {

            responseApi.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseApi.setResponseData("오류");
            responseApi.setResponseMessage(e.getMessage());

        }

        return ResponseEntity.ok(responseApi);

    }

    @PutMapping("/update")
    public ResponseEntity<ResponseApi> updatePost(
            @RequestBody PostEntity post,
            @AuthenticationPrincipal UserDetailsModel userDetailsModel
    ) {

        ResponseApi responseApi = new ResponseApi();

        try {

            if (userDetailsModel == null && userDetailsModel.getAccount_type().matches("GUEST")) {

                responseApi.setResponseCode(HttpStatus.UNAUTHORIZED.value());
                responseApi.setResponseData("FAIL");
                responseApi.setResponseMessage("외부 사용자는 글 수정을 허용하지 않습니다.");

            } else {

                Optional<PostEntity> getPost = postService.getPost(post);

                if(getPost.isPresent()) {

                    PostEntity presentPost = getPost.get();

                    if(presentPost.getDelete_yn().matches("N")) {

                        post.setUpdate_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                        postService.savePost(post);

                        responseApi.setResponseCode(HttpStatus.OK.value());
                        responseApi.setResponseData("수정 완료");
                        responseApi.setResponseMessage(HttpStatus.OK.name());

                    } else {

                        responseApi.setResponseCode(HttpStatus.NOT_FOUND.value());
                        responseApi.setResponseData("삭제된 게시글입니다.");
                        responseApi.setResponseMessage(HttpStatus.NOT_FOUND.name());

                    }

                } else {

                    responseApi.setResponseCode(HttpStatus.NOT_FOUND.value());
                    responseApi.setResponseData("등록되어 있지 않은 게시글입니다.");
                    responseApi.setResponseMessage(HttpStatus.NOT_FOUND.name());

                }

            }

        } catch(Exception e) {

            responseApi.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseApi.setResponseData("오류");
            responseApi.setResponseMessage(e.getMessage());

        }

        return ResponseEntity.ok(responseApi);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseApi> deletePost(
            @RequestBody PostEntity post
    ) {

        ResponseApi responseApi = new ResponseApi();

        try {

            postService.deletePost(post);

            responseApi.setResponseCode(HttpStatus.OK.value());
            responseApi.setResponseData("삭제 완료");
            responseApi.setResponseMessage(HttpStatus.OK.name());

        } catch(Exception e) {

            responseApi.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseApi.setResponseData("오류");
            responseApi.setResponseMessage(e.getMessage());

        }

        return ResponseEntity.ok(responseApi);

    }

}
