package com.Ecorp.solution.DAO;

import com.Ecorp.solution.model.Server;
import com.Ecorp.solution.repository.ServerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ServerDAO {

    private final ServerRepository serverRepository;

    public ServerDAO(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    public Optional<Server> getServerById(Long id) {
        return serverRepository.findById(id);
    }

    public List<Server> getAllServers() {
        return serverRepository.findAll();
    }

}
