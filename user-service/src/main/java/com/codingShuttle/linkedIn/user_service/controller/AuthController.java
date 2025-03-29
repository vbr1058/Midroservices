package com.codingShuttle.linkedIn.user_service.controller;

import com.codingShuttle.linkedIn.user_service.dto.LoginRequestDto;
import com.codingShuttle.linkedIn.user_service.dto.SignUpRequestDto;
import com.codingShuttle.linkedIn.user_service.dto.UserDto;
import com.codingShuttle.linkedIn.user_service.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.user_service.controller
 * @since 24/09/2024 - 03:04 pm
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/signUp")
    @Transactional
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        UserDto userDto= authService.signUp(signUpRequestDto);
        return ResponseEntity.ok(userDto);
    }


    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) throws Throwable {
        String accessToken= authService.loginService(loginRequestDto);
        return new ResponseEntity<>(accessToken, HttpStatus.OK);
    }
}
