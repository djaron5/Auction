package com.example.Auction.service;

import com.example.Auction.domain.Role;
import com.example.Auction.domain.User;
import com.example.Auction.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

//import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean addUser(User user) {
        User byLogin = userRepo.findByUsername(user.getUsername());

        if (byLogin != null) {
            return false;
        }

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.USER);
        if (((List<User>)userRepo.findAll()).size() == 0)
            roleSet.add(Role.ADMIN);
        user.setRoles(roleSet);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        sendMessage(user);


        userRepo.save(user);
        return true;
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            user.setActivationCode(UUID.randomUUID().toString());
            String emailMessage = String.format("Hello %s!\nWelcome to auction app.\n" +
                    "Please, visit next link http://localhost:8080/activate/%s",
            user.getUsername(), user.getActivationCode());
            mailSender.send(user.getEmail(), "ActivationCode", emailMessage);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);

        userRepo.save(user);
        return true;
    }

    public List<User> findAll() {
        return (List<User>) userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> roles) {
        user.setUsername(username);
        Set<String> rolesSet = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key: roles.keySet()) {
            if (rolesSet.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }

    public void updateProfile(User user, String password, String email) {
        String oldEmail = user.getEmail();

        boolean isEmailChanged = email != null && !email.equals(oldEmail);

        if (isEmailChanged)
            user.setEmail(email);

        user.setActivationCode(UUID.randomUUID().toString());

        if (!StringUtils.isEmpty(password))
            user.setPassword(password);

        userRepo.save(user);

        if (isEmailChanged)
            sendMessage(user);
    }

    public boolean isPasswordsEquals(User user, String newPassword) {
        System.out.println(user.getPassword());
        System.out.println(passwordEncoder.encode(newPassword));
        return user.getPassword().equals(passwordEncoder.encode(newPassword));
    }

    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
    }
}
