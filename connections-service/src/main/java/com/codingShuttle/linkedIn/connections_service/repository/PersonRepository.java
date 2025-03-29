package com.codingShuttle.linkedIn.connections_service.repository;

import com.codingShuttle.linkedIn.connections_service.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Vinay B R
 * @project Live LinkedIn
 * @package com.codingShuttle.linkedIn.connections_service.repository
 * @since 07/10/2024 - 11:38 pm
 */
public interface PersonRepository extends Neo4jRepository<Person,Long> {
    Optional<Person> getByName(String name);

    @Query("match (personA:Person) - [:CONNECTED_TO] -> (personB:Person) " +
            "where personA.userId=$userId return personB ")
    List<Person> getFirstDegreeConnections(Long userId);


    @Query("MATCH (p1:Person)-[r:REQUESTED_TO]->(p2:Person) " +
            "where p1.userId=$senderId and p2.userId=$receiverId RETURN count(r)>0")
    Boolean connectionRequestExists(Long senderId,Long receiverId);

    @Query("MATCH (p1:Person)-[r:CONNECTED_TO]->(p2:Person) " +
            "where p1.userId=$senderId and p2.userId=$receiverId RETURN count(r)>0")
    Boolean alreadyConnected(Long senderId,Long receiverId);


    @Query("MATCH (p1:Person), (p2:Person) " +
            "where p1.userId=$senderId and p2.userId=$receiverId " +
            "CREATE (p1)-[:REQUESTED_TO]->(p2)")
    Boolean addConnectionRequest(Long senderId,Long receiverId);

    @Query("MATCH (p1:Person)-[r:REQUESTED_TO]->(p2:Person)\n" +
            "WHERE p1.userId = $senderId AND p2.userId = $receiverId\n" +
            "DELETE r\n" +
            "CREATE (p1)-[:CONNECTED_TO]->(p2)\n")
    void acceptConnectionRequest(Long senderId, Long receiverId);

    @Query("MATCH (p1:Person)-[r:REQUESTED_TO]->(p2:Person) " +
            "where p1.userId=$senderId and p2.userId=$receiverId " +
            "DELETE r")
    boolean rejectConnectionRequest(Long senderId, Long receiverId);
}
