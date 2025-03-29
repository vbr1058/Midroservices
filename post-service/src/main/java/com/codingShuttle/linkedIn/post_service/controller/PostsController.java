package com.codingShuttle.linkedIn.post_service.controller;

import com.codingShuttle.linkedIn.post_service.auth.UserContextHolder;
import com.codingShuttle.linkedIn.post_service.dto.PostCreateRequestDto;
import com.codingShuttle.linkedIn.post_service.dto.PostDto;
import com.codingShuttle.linkedIn.post_service.entity.Post;
import com.codingShuttle.linkedIn.post_service.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.controller
 * @since 23/09/2024 - 01:08 am
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class PostsController {

    private final PostService postService;

    @PostMapping(path = "/CreatePost")
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postDto, HttpServletRequest httpServletRequest){
        PostDto createdPost= postService.createPost(postDto);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping(path = "/getPost/{postId}")
    public ResponseEntity<PostDto> getUserByUserName(HttpServletRequest httpServletRequest,@PathVariable Long postId) {
       return postService.getPostById(postId);
    }

    @GetMapping(path = "/users/allPosts")
    public ResponseEntity<List<PostDto>> listOfPostByUserId(HttpServletRequest httpServletRequest) {
//        String userId=httpServletRequest.getHeader("X-User-Id");
//        log.info("Getting all the POSTS for the userId: "+userId);
        String userId= String.valueOf(UserContextHolder.getCurrentUserId());
        return new ResponseEntity<>(postService.getAllPostOfUser(Long.parseLong(userId)),HttpStatus.OK);
    }

}
