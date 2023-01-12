package com.Ecorp.solution.controller;

import com.Ecorp.solution.model.JWTPayload;
import com.Ecorp.solution.model.User;
import com.Ecorp.solution.record.ChangePassword;
import com.Ecorp.solution.record.LoginRequest;
import com.Ecorp.solution.record.RegisterRequest;
import com.Ecorp.solution.record.UpdateRole;
import com.Ecorp.solution.service.UserService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class UserController {
    private static final String SERVER_GOT_ERROR = "Er is iets fout gegaan op de server, probeer het later opnieuw";
    private static final String NEW_USER_MADE = "Nieuwe gebruiker aangemaakt";
    private static final String USER_ALREADY_EXISTS = "gebuiker bestaat al";
    private static final String INVALID_PASSWORD = "Ongeldige inloggegevens";
    private static final String CHANGED_PASSWORD_SUCCESS = "Uw nieuwe wachtwoord is opgeslagen";
    private static final String PASSWORD_DOES_NOT_MATCH = "Wachtwoord komt niet overeen";
    @Autowired private UserService userService;
    @Autowired private AuthenticationManager authManager;

    @PostMapping("/register")
    public JWTPayload registerHandler(@RequestBody RegisterRequest registerRequest){
        try {
            String token = userService.register(registerRequest);
            if (token.contains(USER_ALREADY_EXISTS)) return new JWTPayload("", USER_ALREADY_EXISTS, false);
            return new JWTPayload(token, NEW_USER_MADE, true);
        } catch (AuthenticationException e) {
            return new JWTPayload("", SERVER_GOT_ERROR, false);
        }
    }

    @PostMapping("/login")
    public JWTPayload loginHandler(@RequestBody LoginRequest body){
        try {
            String token = userService.login(body, authManager);
            return new JWTPayload(token, "", true);
        }catch (AuthenticationException authExc){
            return new JWTPayload("", INVALID_PASSWORD, false);
        }
    }

    @PostMapping("/changepassword")
    public JWTPayload changePassword(@RequestBody ChangePassword body){
        try {
            String token = userService.changePassword(body, authManager);
            if (token.equals(PASSWORD_DOES_NOT_MATCH)) {
                return new JWTPayload("", PASSWORD_DOES_NOT_MATCH, false);
            }
            return new JWTPayload(token, CHANGED_PASSWORD_SUCCESS, true);
        }catch (AuthenticationException authExc){
            return new JWTPayload("", PASSWORD_DOES_NOT_MATCH, false);
        }
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public String getUserByID(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) return user.get().getName();
        return "";
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/updateRole")
    public void deleteUser(@RequestBody UpdateRole r) {
        userService.updateRole(r.role(), r.id());
    }
}