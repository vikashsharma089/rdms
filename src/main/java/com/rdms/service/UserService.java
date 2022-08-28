package com.rdms.service;

import com.rdms.config.CustomPasswordEncoder;
import com.rdms.model.Role;
import com.rdms.model.Users;
import com.rdms.model.Village;
import com.rdms.repository.RoleRepository;
import com.rdms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;
    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Users findUserByUserName(String userName) {
        userName = userName.toLowerCase();
        return userRepository.findByUserName(userName);
    }

    public Users saveUser(Users users) {
        users.setActive(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        users.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(users);
    }

    public Village getVillage(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<String,Object> details = (Map<String, Object>) auth.getDetails();
        Users users = (Users) details.get("userDetail");
        return users.getVillage();
    }
}
