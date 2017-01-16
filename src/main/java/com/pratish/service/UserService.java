package com.pratish.service;

import com.pratish.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    void saveAdmin(User user);

    void saveSuperUser(User user);
}
