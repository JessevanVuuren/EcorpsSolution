package com.Ecorp.solution.service;

import com.Ecorp.solution.DAO.ServerDAO;
import com.Ecorp.solution.model.Server;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServerService {
    ServerDAO serverDAO;

    public List<Server> getAllServers() {
        return serverDAO.getAllServers();
    }
}
