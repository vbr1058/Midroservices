package com.codingShuttle.linkedIn.user_service.service;

import com.codingShuttle.linkedIn.user_service.clients.CreateNode;
import com.codingShuttle.linkedIn.user_service.entity.Person;
import com.codingShuttle.linkedIn.user_service.exception.BadRequestException;
import com.codingShuttle.linkedIn.user_service.exception.ResourceNotFoundException;
import com.codingShuttle.linkedIn.user_service.dto.LoginRequestDto;
import com.codingShuttle.linkedIn.user_service.dto.SignUpRequestDto;
import com.codingShuttle.linkedIn.user_service.dto.UserDto;
import com.codingShuttle.linkedIn.user_service.entity.User;
import com.codingShuttle.linkedIn.user_service.repository.UserRepository;
import com.codingShuttle.linkedIn.user_service.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.user_service.service
 * @since 24/09/2024 - 03:12 pm
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtService jwtService;


    @Autowired
    private CreateNode createNode;

    public UserDto signUp(SignUpRequestDto signUpRequestDto) {
        User user=modelMapper.map(signUpRequestDto,User.class);
        user.setPassword(PasswordUtil.hashPassword(signUpRequestDto.getPassword()));
        if (userRepository.existsByEmail(signUpRequestDto.getEmail())){
            throw new NullPointerException("User is already present with email: "+signUpRequestDto.getEmail());
        }
        User savedUser=userRepository.save(user);

        log.info("Person is created Successfully:");
        Person person=new Person();
        person.setId(user.getId());
        person.setName(user.getName());
        person.setUserId(user.getId());

        Person createdPerson=createNode.createNewNode(person);
        log.info("Person is created in the Neo4j Database: {}",createdPerson);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public UserDto logIn(SignUpRequestDto signUpRequestDto) {
        User user=modelMapper.map(signUpRequestDto,User.class);
        user.setPassword(PasswordUtil.hashPassword(signUpRequestDto.getPassword()));
        User savedUser=userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public String loginService(LoginRequestDto loginRequestDto) throws Throwable {
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email " + loginRequestDto.getEmail()));
        boolean isPasswordMatch=PasswordUtil.checkPassword(loginRequestDto.getPassword(),user.getPassword());
        if (!isPasswordMatch){
            throw new BadRequestException("Incorrect Password is entered");
        }
        return jwtService.generateAccessToken(user);
    }
}
