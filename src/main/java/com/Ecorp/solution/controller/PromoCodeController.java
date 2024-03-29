package com.Ecorp.solution.controller;


import com.Ecorp.solution.model.JWTPayload;
import com.Ecorp.solution.model.Promocode;
import com.Ecorp.solution.model.Server;
import com.Ecorp.solution.model.User;
import com.Ecorp.solution.record.ChangePassword;
import com.Ecorp.solution.record.LoginRequest;
import com.Ecorp.solution.record.RegisterRequest;
import com.Ecorp.solution.service.PromocodeService;
import com.Ecorp.solution.service.ServerService;
import com.Ecorp.solution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/api/promocode")
public class PromoCodeController {

    @Autowired
    PromocodeService promocodeService;

    @GetMapping
    public List<Promocode> getAllServers() {
        return promocodeService.getAllPromocodes();
    }

    @GetMapping(value = "/check/{code}")
    public double checkCode(@PathVariable String code) {
        return promocodeService.checkCode(code);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteServer(@PathVariable Long id) {
        promocodeService.delete(id);
    }

    @PutMapping(value = "/update")
    public void updateServer(@RequestBody Promocode e) {
        promocodeService.update(e);
    }

    @PostMapping(value = "/new")
    public void newServer(@RequestBody Promocode p) {
        promocodeService.create(p);
    }

}