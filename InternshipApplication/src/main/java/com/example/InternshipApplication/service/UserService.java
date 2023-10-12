package com.example.InternshipApplication.service;

import com.example.InternshipApplication.configuration.MyUserDetails;
import com.example.InternshipApplication.model.User;
import com.example.InternshipApplication.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User Not found"));
        return new MyUserDetails(user);
    }

    @Transactional
    public User createUser(User user){
        User newUser=User.builder()
                .username(user.getUsername())
                .password(bCryptPasswordEncoder.encode(user.getPassword()))
                .role("ROLE_EMPLOYER")
                .build();
        userRepository.save(newUser);

        return newUser;
    }

    public User findUserByUserName(String username){
        User user=userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));

        return user;
    }
    public User findUserById(int id){
        User user=userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return user;
    }

    public List<User> findAllUsers(){
        List<User> users=userRepository.findAll();
        return users;
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
}
