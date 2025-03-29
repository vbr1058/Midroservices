package com.codingShuttle.linkedIn.post_service.controller;

import com.codingShuttle.linkedIn.post_service.service.PostLikeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.controller
 * @since 23/09/2024 - 11:38 pm
 */

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class LikeController{

    private final PostLikeService postLikeService;
    @PostMapping(path = "/like/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId, HttpServletRequest httpServletRequest){
        String userId=httpServletRequest.getHeader("X-User-Id");
        log.info("Getting the Likes for the User ID: "+userId);
//        postLikeService.likePost(postId,Long.parseLong(httpServletRequest.getHeader("X-User-Id")));
        postLikeService.likePost(postId);
        log.info("Got all the likes of the user with ID: "+userId);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping(path = "/unlike/{postId}")
    @Transactional
    public ResponseEntity<Void> unlikePost(@PathVariable Long postId){
        postLikeService.unLikePost(postId);
        return ResponseEntity.noContent().build();
    }
}
