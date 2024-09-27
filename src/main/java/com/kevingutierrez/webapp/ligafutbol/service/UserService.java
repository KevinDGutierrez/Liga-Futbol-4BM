package com.kevingutierrez.webapp.ligafutbol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevingutierrez.webapp.ligafutbol.model.User;
import com.kevingutierrez.webapp.ligafutbol.repository.UserRepository;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository ;

    @Override
    public List<User> listarUser() {
        return userRepository.findAll();
    }

    @Override
    public User guardarUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User buscarUserPorId(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarUser(User user) {
        userRepository.delete(user);
    }
}
