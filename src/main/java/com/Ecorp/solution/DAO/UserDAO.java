package com.Ecorp.solution.DAO;

import com.Ecorp.solution.model.User;
import com.Ecorp.solution.model.UserRole;
import com.Ecorp.solution.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDAO {

    private final UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updatePassword(Long id, User newUser) {
        userRepository.updatePassword(newUser.getPassword(), id);
        return newUser;

    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void updateRole(UserRole role, Long id) {
        userRepository.updateRole(role, id);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllNonAdmins();
    }
}
