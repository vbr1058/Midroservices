package com.codingShuttle.linkedIn.post_service.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Vinay B R
 * @project Live LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.auth
 * @since 20/10/2024 - 12:41 am
 */

/*This Class is used to mutate the request before calling a different microservice.
    here it is used to add parameter to a header before calling to a different microservice*/

@Component
public class PostServiceRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("X-User-Id",UserContextHolder.getCurrentUserId().toString());
    }
}
