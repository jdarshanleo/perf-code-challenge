package com.challenge.user.controller;


import com.challenge.user.persistence.UserService;
import com.challenge.user.model.UserRequestSto;
import com.challenge.user.model.UserCreationResponseAto;
import com.challenge.user.persistence.domain.UpgradeUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/user/v1")
public class UserController {

    private final UserService userSrvc;

    @Autowired
    public UserController(UserService userSrvc){
        this.userSrvc = userSrvc;
    }

    @PostMapping("/create")
    @Transactional(timeout = 10)
    public ResponseEntity<UserCreationResponseAto> createUser(@RequestBody @Validated UserRequestSto user){
        log.info("Creating User {} {}", user.getFirstName(), user.getLastName());

        UserCreationResponseAto userConfirmation = userSrvc.createUser(user);
        return new ResponseEntity<>(userConfirmation, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    @Transactional(timeout = 10)
    public ResponseEntity<UpgradeUser> getUserById(@PathVariable("id") UUID uuid){
        log.info("Searching for user {}", uuid);

        UpgradeUser upgradeUser = userSrvc.findUserById(uuid);
        return new ResponseEntity<>(upgradeUser, HttpStatus.OK);
    }


}
