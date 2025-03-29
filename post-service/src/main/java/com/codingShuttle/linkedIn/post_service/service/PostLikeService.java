package com.codingShuttle.linkedIn.post_service.service;

import com.codingShuttle.linkedIn.post_service.auth.UserContextHolder;
import com.codingShuttle.linkedIn.post_service.entity.Post;
import com.codingShuttle.linkedIn.post_service.events.PostLikeEvent;
import com.codingShuttle.linkedIn.post_service.entity.PostLike;
import com.codingShuttle.linkedIn.post_service.exception.BadRequestException;
import com.codingShuttle.linkedIn.post_service.exception.ResourceNotFoundException;
import com.codingShuttle.linkedIn.post_service.repository.PostLikeRepository;
import com.codingShuttle.linkedIn.post_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.service
 * @since 23/09/2024 - 11:40 pm
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    @Autowired
    private final PostLikeRepository postLikeRepository;

    @Autowired
    private final PostRepository postRepository;


    private final KafkaTemplate<String, String> kafkaTemplate;

    public void likePost(Long postId){
        Long userId=UserContextHolder.getCurrentUserId();
        log.info("The Current User ID is: {}",userId);
        log.info("Attempting to Like the Post with ID: "+postId);
        Post post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("PostNotFound With Id: "+ postId));

        boolean alreadyLikedPost=postLikeRepository.existsByUserIdAndPostId(userId,postId);
        if (alreadyLikedPost){
            throw new BadRequestException("Cannot Like the Same Post Again: "+postId);
        }
        PostLike postLike=new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikeRepository.save(postLike);
        log.info("Post is Liked successfully with ID: "+postId);
        PostLikeEvent postLikEvent=PostLikeEvent.builder()
                .postId(postId)
                .creatorId(post.getUserId())
                .likedByUserId(userId)
                .build();

        kafkaTemplate.send("post-liked-topic",String.valueOf(postId),postLikEvent.toString());

    }

    public void unLikePost(Long postId) {
        Long userId= UserContextHolder.getCurrentUserId();
        log.info("Attempting to Un-Like the Post with ID: "+postId);
        boolean userExists= postRepository.existsById(userId);
        if (!userExists){
            throw new ResourceNotFoundException("PostNotFound With Id: "+ userId);
        }
        boolean alreadyLikedPost=postLikeRepository.existsByUserIdAndPostId(userId,postId);
        if (!alreadyLikedPost){
            throw new BadRequestException("Cannot un-Like the Same Post Again: "+postId);
        }
        postLikeRepository.deleteByUserIdAndPostId(userId,postId);
        log.info("Post is Un-Liked successfully with ID: "+postId);
    }
}
