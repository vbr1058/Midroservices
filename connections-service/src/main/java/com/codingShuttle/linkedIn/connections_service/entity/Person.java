package com.codingShuttle.linkedIn.connections_service.entity;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import javax.annotation.processing.Generated;

/**
 * @author Vinay B R
 * @project Live LinkedIn
 * @package com.codingShuttle.linkedIn.connections_service.entity
 * @since 07/10/2024 - 11:39 pm
 */

@Node
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String name;

}
