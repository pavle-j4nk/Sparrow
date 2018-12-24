package com.sparrow.controller.user;

import com.sparrow.model.user.User;
import com.sparrow.response.UserProfileResponse;
import com.sparrow.response.UserResponse;
import com.sparrow.service.impl.user.UserService;
import com.sparrow.service.impl.user.UserDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getUser(Principal principal) {
        return ResponseEntity.ok(new UserProfileResponse(userService.findByEmail(principal.getName())));
    }

    @PostMapping(path = "/me")
    public ResponseEntity updateProfile(Principal principal, @RequestBody @Valid User info) {
        userService.updateUserInfo(principal.getName(), info);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<UserResponse>> search(@PathVariable("name") String name) {
        return ResponseEntity.ok(UserResponse.of(userService.searchByAnyName(name)));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") String id) {
        return ResponseEntity.ok(UserResponse.of(userService.findByEmail(id)));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile(Principal principal) {
        return null;
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserProfileResponse> getProfile(@PathVariable("id") String id) {
        return null;
    }

    @ExceptionHandler({UserDoesNotExistException.class})
    public ResponseEntity<Object> onException() {
        return ResponseEntity.notFound().build();
    }

}