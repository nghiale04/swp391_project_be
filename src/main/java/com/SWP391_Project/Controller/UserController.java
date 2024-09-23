package com.SWP391_Project.Controller;

import com.SWP391_Project.Entity.User;
import com.SWP391_Project.Service.UserService;
import com.SWP391_Project.model.UserLoginDTO;
import com.SWP391_Project.model.UserRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
        public ResponseEntity register(@RequestBody UserRegisterDTO userRegisterDTO) throws Exception {
       try {
           if(!userRegisterDTO.getPassword().equals(userRegisterDTO.getRetypePassword())){
               return ResponseEntity.badRequest().body("Password not match");
           }
           User user = userService.createUser(userRegisterDTO);
           return ResponseEntity.ok("Register successfully");
       }catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
    }
    @PostMapping("/login")
    public ResponseEntity login(
            @Valid @RequestBody UserLoginDTO userLoginDTO) {
        try {
            String token = userService.login(userLoginDTO.getPhoneNumber(), userLoginDTO.getPassword());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
