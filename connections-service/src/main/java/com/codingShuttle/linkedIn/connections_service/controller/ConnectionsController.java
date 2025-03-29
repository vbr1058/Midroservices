package com.codingShuttle.linkedIn.connections_service.controller;

import com.codingShuttle.linkedIn.connections_service.entity.Person;
import com.codingShuttle.linkedIn.connections_service.service.ConnectionsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Vinay B R
 * @project Live LinkedIn
 * @package com.codingShuttle.linkedIn.connections_service.controller
 * @since 07/10/2024 - 11:38 pm
 */
@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ConnectionsController {

    private final ConnectionsService connectionsService;

    @GetMapping("/loggedIn/first-connections")
    public ResponseEntity<List<Person>> getFirstDegreeConnection(HttpServletRequest httpServletRequest){
        return ResponseEntity.ok(connectionsService.getAllFirstDegreeConnection(Long.valueOf(httpServletRequest.getHeader("X-User-Id"))));
    }

    @GetMapping("/first-connections")
    public ResponseEntity<List<Person>> getFirstDegreeConnectionOfUser(@RequestParam Long userId){
        return ResponseEntity.ok(connectionsService.getAllFirstDegreeConnection(userId));
    }


    @GetMapping("/requestConnection/{userId}")
    @Transactional
    public ResponseEntity<Boolean> sendConnectionRequest(@PathVariable Long userId){
        return ResponseEntity.ok(connectionsService.sendConnectionRequest(userId));
    }


    @PostMapping("/acceptConnection/{userId}")
    public ResponseEntity<Boolean> acceptConnectionRequest(@PathVariable Long userId){
        return ResponseEntity.ok(connectionsService.acceptConnectionRequest(userId));
    }


    @PostMapping("/rejectConnection/{userId}")
    public ResponseEntity<Boolean> rejectConnectionRequest(@PathVariable Long userId){
        return ResponseEntity.ok(connectionsService.rejectConnectionRequest(userId));
    }


    @PostMapping("/createUser")
    public ResponseEntity<Person> createNewNode(@RequestBody Person person){
        return ResponseEntity.ok(connectionsService.createUser(person));
    }
}
