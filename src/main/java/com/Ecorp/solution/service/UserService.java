package com.Ecorp.solution.service;

import com.Ecorp.solution.model.UserRole;
import java.util.List;
import lombok.AllArgsConstructor;
import com.Ecorp.solution.DAO.UserDAO;
import com.Ecorp.solution.model.User;
import com.Ecorp.solution.record.ChangePassword;
import com.Ecorp.solution.record.LoginRequest;
import com.Ecorp.solution.repository.UserRepository;
import com.Ecorp.solution.security.JWTUtil;
import com.Ecorp.solution.record.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private static final String USER_ALREADY_EXISTS = "gebuiker bestaat al";
    private static final String COULD_NOT_FIND_USER_WITH_EMAIL = "Could not findUser with email:";
    private static final String PASSWORD_DOES_NOT_MATCH = "Wachtwoord komt niet overeen";

    private final String ROLE_PREFIX = "ROLE_";
    private UserDAO userDAO;
    @Autowired private JWTUtil jwtUtil;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserRepository userRepository;

    /**
     * Match user credentials with de credentials in database.
     * @param request - email and password
     * @param authManager
     * @return JWT token if user exists and credentials match
     */
    public String login(LoginRequest request, AuthenticationManager authManager) {
        UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        authManager.authenticate(authInputToken);
        User user = userDAO.getUserByEmail(request.email()).get();
        return jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getName(), user.getId());
    }

    /**
     * Get User model form database with email.
     * @param email - user email
     * @return - user model
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userRes = userDAO.getUserByEmail(email);
        if (userRes.isEmpty()) throw new UsernameNotFoundException(COULD_NOT_FIND_USER_WITH_EMAIL + email);
        User user = userRes.get();


        return new org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole())));
    }

    /**
     * Register new user in database and generate new JWT token.
     * @param registerRequest - email, name, password, role.
     * @return - new JWT token.
     */
    public String register(RegisterRequest registerRequest)  {
        Optional<User> user = userDAO.getUserByEmail(registerRequest.email());
        if (user.isPresent()) return USER_ALREADY_EXISTS;

        String encodedPass = passwordEncoder.encode(registerRequest.password());
        User newUser = new User(registerRequest.name(), registerRequest.email(), UserRole.USER, encodedPass);
        newUser.setPassword(encodedPass);
        userRepository.save(newUser);
        return jwtUtil.generateToken(newUser.getEmail(), newUser.getRole(), newUser.getName(), newUser.getId());
    }

    public Optional<User> getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    public String changePassword(ChangePassword body, AuthenticationManager authManager) {
        Optional<User> userRes = userDAO.getUserByEmail(body.email());
        if (userRes.isEmpty()) throw new UsernameNotFoundException(COULD_NOT_FIND_USER_WITH_EMAIL + body.email());
        User user = userRes.get();

        UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(body.email(), body.oldPassword());
        authManager.authenticate(authInputToken);

        String encodedPass = passwordEncoder.encode(body.newPassword());
        user.setPassword(encodedPass);
        userDAO.updatePassword(user.getId(),user);

        return jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getName(), user.getId());
    }

    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    public void updateRole(UserRole role, Long id) {
        userDAO.updateRole(role, id);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}