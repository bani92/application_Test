package me.whiteship.inflearnjavatest.toyProject.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.whiteship.inflearnjavatest.toyProject.model.dto.UserCreateDto;
import me.whiteship.inflearnjavatest.toyProject.model.dto.UserResponse;
import me.whiteship.inflearnjavatest.toyProject.repository.UserEntity;
import me.whiteship.inflearnjavatest.toyProject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저(users)")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserCreateController {

    private final UserController userController;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateDto userCreateDto) {
        UserEntity userEntity = userService.create(userCreateDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userController.toResponse(userEntity));
    }

}