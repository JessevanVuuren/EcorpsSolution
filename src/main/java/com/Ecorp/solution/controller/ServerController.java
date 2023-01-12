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

    @Autowired
    ServerService serverService;

    @GetMapping
    public List<Server> getAllServers() {
        return serverService.getAllServers();
    }

    @DeleteMapping(value = "/delete/{id}")
    @CrossOrigin(origins = "http://174.138.8.53:80")
//    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteServer(@PathVariable Long id) {
        serverService.deleteServer(id);
    }

    @PutMapping(value = "/update")
    @CrossOrigin(origins = "http://174.138.8.53:80")
//    @CrossOrigin(origins = "http://localhost:4200")
    public Server updateServer(@RequestBody Server server) {
        return serverService.updateServer(server);
    }

    @PostMapping(value = "/new")
    @CrossOrigin(origins = "http://174.138.8.53:80")
//    @CrossOrigin(origins = "http://localhost:4200")
    public Server newServer(@RequestBody Server server) {
        return serverService.newServer(server);
    }
}