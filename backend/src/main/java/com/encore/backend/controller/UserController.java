package com.encore.backend.controller;

import com.encore.backend.dto.UserDto;
import com.encore.backend.service.UserService;
import com.encore.backend.vo.ResponseUser;
import com.encore.backend.vo.UserVO;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody UserDto userDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        userService.createUser(userDto);
        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable String email) {
        UserDto userDto = userService.getUserDetailsByEmail(email);
        ResponseUser responseUser = new ModelMapper().map(userDto, ResponseUser.class);
        return ResponseEntity.status(userDto == null ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(responseUser);
    }

    @PutMapping("/users/{email}")
    public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody UserVO user) {
        boolean result = userService.updateUserByEmail(email, user);

        return ResponseEntity.status(result ? HttpStatus.CREATED : HttpStatus.NO_CONTENT)
                .body("update user " + (result ? "suceess" : "fail"));
    }

    @DeleteMapping("/users/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        boolean result = userService.deleteUserByEmail(email);

        return ResponseEntity.status(result ? HttpStatus.CREATED : HttpStatus.NO_CONTENT)
                .body("delete user " + (result ? "suceess" : "fail"));
    }
}
