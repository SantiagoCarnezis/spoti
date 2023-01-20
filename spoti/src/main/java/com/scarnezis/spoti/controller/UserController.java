package com.scarnezis.spoti.controller;

import com.scarnezis.spoti.dto.UserInDTO;
import com.scarnezis.spoti.domain.User;
import com.scarnezis.spoti.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserController {

  private final UserService userService;

  @PostMapping
  public User signIn(@RequestBody UserInDTO userInDTO){
    return this.userService.signIn(userInDTO);
  }

  @GetMapping("/{user_id}")
  public ResponseEntity<User> getUser(@PathVariable("user_id") Long userId){
    return ResponseEntity.ok(userService.get(userId));
  }
}
