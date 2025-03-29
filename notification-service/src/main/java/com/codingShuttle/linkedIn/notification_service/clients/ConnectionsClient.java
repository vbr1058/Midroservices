package com.codingShuttle.linkedIn.notification_service.clients;

import com.codingShuttle.linkedIn.notification_service.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Vinay B R
 * @project Live LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.clients
 * @since 19/10/2024 - 12:07 am
 */
@FeignClient(name = "connections-service",path = "/connections")
public interface ConnectionsClient {

    @GetMapping("/core/first-connections")
    List<PersonDto> getFirstDegreeConnection(@RequestParam Long userId);
//    List<PersonDto> getFirstDegreeConnection(@RequestHeader("X-User-Id") Long userId );

}
