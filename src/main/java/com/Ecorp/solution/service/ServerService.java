package com.Ecorp.solution.service;

import com.Ecorp.solution.DAO.ServerDAO;
import com.Ecorp.solution.model.Server;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServerService {
    ServerDAO serverDAO;

    public List<Server> getAllServers() {
        return serverDAO.getAllServers();
    }

    public Server newServer(Server server) {
        return serverDAO.newServer(server);
    }

    public Server updateServer(Server server) {
        return serverDAO.updateServer(server);
    }

    public void deleteServer(Long id) {
        serverDAO.deleteServer(id);
    }
}
