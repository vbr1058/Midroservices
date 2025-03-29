package com.codingShuttle.linkedIn.post_service.clients;

import com.codingShuttle.linkedIn.post_service.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Vinay B R
 * @project Live LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.clients
 * @since 19/10/2024 - 12:07 am
 */
@FeignClient(name = "connections-service",path = "/connections")
public interface ConnectionsClient {

    @GetMapping("/core/loggedIn/first-connections")
    List<PersonDto> getFirstDegreeConnection();

}
