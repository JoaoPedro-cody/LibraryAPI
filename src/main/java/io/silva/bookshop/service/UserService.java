package io.silva.bookshop.service;

import io.silva.bookshop.model.User;
import io.silva.bookshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> searchUsers(){
        return userRepository.findAll();
    }

    public User searchUserEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User updateUser(User user, String email){
        User byEmail = userRepository.findByEmail(email);
        byEmail.setEmail(user.getEmail());
        byEmail.setName(user.getName());
        userRepository.save(byEmail);

        return byEmail;
    }

    public void deleteUser(String email){
        userRepository.deleteUserByEmail(email);
    }
}
