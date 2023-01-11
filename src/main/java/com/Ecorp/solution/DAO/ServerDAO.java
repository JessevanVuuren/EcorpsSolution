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

    public Server newServer(Server server) {
        return serverRepository.save(server);
    }

    public Server updateServer(Server server) {
        serverRepository.updateServer(
                server.getCpu(),
                server.getName(),
                server.getPrice(),
                server.getRam(),
                server.getSpace(),
                server.getStype(),
                server.getId()
                );
        Optional<Server> s = getServerById(server.getId());
        return s.orElse(null);

    }

    public void deleteServer(Long id) {
        serverRepository.deleteById(id);
    }
}
