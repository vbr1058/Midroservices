package com.codingShuttle.linkedIn.post_service.repository;

import com.codingShuttle.linkedIn.post_service.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.repository
 * @since 23/09/2024 - 11:41 pm
 */
public interface PostLikeRepository extends JpaRepository<PostLike,Long> {
    boolean existsByUserIdAndPostId(Long userId,Long postId);

    void deleteByUserIdAndPostId(long userId, Long postId);
}
