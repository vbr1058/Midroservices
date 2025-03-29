package com.codingShuttle.linkedIn.user_service.repository;

import com.codingShuttle.linkedIn.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.user_service.repository
 * @since 24/09/2024 - 03:14 pm
 */
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
