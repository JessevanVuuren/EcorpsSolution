package com.Ecorp.solution.controller;

import com.Ecorp.solution.model.Server;
import com.Ecorp.solution.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/server")
public class ServerController {

    @Autowired ServerService serverService;

    @GetMapping
    public List<Server> getAllServers() {
        return serverService.getAllServers();
    }
}