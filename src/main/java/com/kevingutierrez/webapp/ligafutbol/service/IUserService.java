package com.kevingutierrez.webapp.ligafutbol.service;

import java.util.List;

import com.kevingutierrez.webapp.ligafutbol.model.User;

public interface IUserService {
    public List<User> listarUser();

    public User guardarUser(User user);

    public User buscarUserPorId(Long id);

    public void eliminarUser(User user);
}
