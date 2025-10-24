package io.silva.bookshop.service;

import io.silva.bookshop.NotFoundException;
import io.silva.bookshop.model.User;
import io.silva.bookshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;

    public void saveUser(User user) {
        User byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail == null){
            userRepository.save(user);
        }else {
            throw new RuntimeException("This email has already been registered!");
        }
    }

    public List<User> searchUsers(String name, String email){
        List<User> all = userRepository.findAll();
        if (name != null){
            return userRepository.findByNameContainingIgnoreCase(name);
        } else if (email != null){
            return List.of(searchUserEmail(email));
        } else{
            if (all.isEmpty()){
                throw new RuntimeException("No registered users!");
            } else {
                return all;
            }
        }
    }

    public User searchUserEmail(String email){
        User byEmail = userRepository.findByEmail(email);
        if (byEmail == null){
            throw new NotFoundException("User not found!");
        }else {
            return byEmail;
        }
    }

    public void updateUser(User user, String email){
        User byEmail = searchUserEmail(email);
        byEmail.setEmail(user.getEmail());
        byEmail.setName(user.getName());
    }

    public void deleteUser(String email){
        userRepository.delete(searchUserEmail(email));
    }
}
