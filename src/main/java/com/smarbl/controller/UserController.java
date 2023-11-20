package com.smarbl.controller;

import com.smarbl.entity.LoginCredentials;
import com.smarbl.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @PostMapping("/login")
    public String Login(@RequestBody User user) {
        String userId = user.getEmail();
        String password = user.getPassword();

        if (userId.equals(LoginCredentials.USER1.getLoginId()) && password.equals(LoginCredentials.USER1.getPassword())) {
            return "Successfully Login";
        } else {
            return "Failed";
        }
    }
}
