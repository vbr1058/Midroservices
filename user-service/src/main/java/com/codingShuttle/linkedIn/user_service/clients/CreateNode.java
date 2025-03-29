package com.codingShuttle.linkedIn.user_service.clients;

import com.codingShuttle.linkedIn.user_service.entity.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.user_service.clients
 * @since 04/11/2024 - 11:38 pm
 */
@FeignClient(name = "connections-service",path = "/connections")
public interface CreateNode {
    @PostMapping("/core/createUser")
    public Person createNewNode(@RequestBody Person person);
}