package com.pratish.service;

import com.pratish.constants.RoleConstants;
import com.pratish.model.Role;
import com.pratish.model.User;
import com.pratish.repository.RoleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BootstrapService implements InitializingBean {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Override
    public void afterPropertiesSet() throws Exception {
        saveRoles();
        saveSuperUser();
        saveAdminUser();
    }

    private void saveRoles() {
        if (roleRepository.count() == 0) {
            List<Role> roles = new ArrayList<>();

            Role role = new Role();
            role.setName(RoleConstants.ROLE_SUPERUSER);
            roles.add(role);

            role = new Role();
            role.setName(RoleConstants.ROLE_ADMIN);
            roles.add(role);

            role = new Role();
            role.setName(RoleConstants.ROLE_USER);
            roles.add(role);

            roleRepository.save(roles);
        }
    }

    private void saveSuperUser() {
        if (userService.findByUsername("superuser") == null) {
            User user = new User();
            user.setUsername("superuser");
            user.setPassword("superuser");
            userService.saveSuperUser(user);
        }
    }

    private void saveAdminUser() {
        if (userService.findByUsername("admin") == null) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword("admin");
            userService.saveAdmin(user);
        }
    }

}
