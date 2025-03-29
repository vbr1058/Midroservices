package com.codingShuttle.linkedIn.post_service.repository;

import com.codingShuttle.linkedIn.post_service.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.repository
 * @since 23/09/2024 - 01:15 am
 */
public interface PostRepository extends JpaRepository<Post ,Long> {
    List<Post> findByUserId(Long userId);
}
