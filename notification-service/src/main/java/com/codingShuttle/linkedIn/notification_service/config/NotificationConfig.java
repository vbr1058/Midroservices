package com.codingShuttle.linkedIn.notification_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.notification_service.config
 * @since 04/11/2024 - 11:00 pm
 */
@Configuration
public class NotificationConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
