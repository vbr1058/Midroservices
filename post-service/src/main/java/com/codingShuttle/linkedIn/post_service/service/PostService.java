package com.codingShuttle.linkedIn.post_service.service;

import com.codingShuttle.linkedIn.post_service.auth.UserContextHolder;
import com.codingShuttle.linkedIn.post_service.auth.UserInterceptor;
import com.codingShuttle.linkedIn.post_service.clients.ConnectionsClient;
import com.codingShuttle.linkedIn.post_service.dto.PersonDto;
import com.codingShuttle.linkedIn.post_service.dto.PostCreateRequestDto;
import com.codingShuttle.linkedIn.post_service.dto.PostDto;
import com.codingShuttle.linkedIn.post_service.entity.Post;
import com.codingShuttle.linkedIn.post_service.events.PostCreatedEvents;
import com.codingShuttle.linkedIn.post_service.exception.ResourceNotFoundException;
import com.codingShuttle.linkedIn.post_service.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.service
 * @since 23/09/2024 - 01:15 am
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final ConnectionsClient connectionsClient;

    private final UserInterceptor userInterceptor;

    private final KafkaTemplate<String, String> kafkaTemplate;
    public PostDto createPost(PostCreateRequestDto postDto) {
        Long userId=UserContextHolder.getCurrentUserId();
        Post post=modelMapper.map(postDto,Post.class);
        post.setUserId(userId);
        Post savedPost=postRepository.save(post);
        PostCreatedEvents postCreatedEvents=PostCreatedEvents.builder()
                .postId(savedPost.getId())
                .creatorId(userId)
                .content(savedPost.getContent()).build();

        String eventId = UUID.randomUUID().toString();
        log.info("Pushing to post-created-topic Kafka topic with ID {}",eventId);
        //sending the Kafka events
        kafkaTemplate.send("post-created-topic", eventId,postCreatedEvents.toString());

        return modelMapper.map(savedPost,PostDto.class);
    }

    public ResponseEntity<PostDto> getPostById(Long postId) {
        List<PersonDto>firstConnection=connectionsClient.getFirstDegreeConnection();

//        TODO send notification to all the connection here once after a post is created
        Post post=postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with Id: "+postId));
        PostDto postDto=modelMapper.map(post,PostDto.class);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }


    public List<PostDto> getAllPostOfUser(Long userId) {
        List<Post> posts=postRepository.findByUserId(userId);
        return posts
                .stream()
                .map((element)-> modelMapper.map(element,PostDto.class))
                .collect(Collectors.toList());
    }
}
