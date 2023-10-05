package kth.distrolab1.bo.controllers;

import kth.distrolab1.bo.entities.User;
import kth.distrolab1.bo.services.UserService;
import kth.distrolab1.bo.services.UserServiceImpl;
import kth.distrolab1.ui.dtos.UserDTO;

import java.util.Date;
import java.util.List;

public class UserController {
    private UserService userService = new UserServiceImpl();

    public UserDTO login(String username, String password) {
        if (username != null && password != null) {
            User user = userService.login(username, password);
            if (user != null) {
                return new UserDTO(user.getUsername(), user.getEmail(), user.getFullName(), user.getRoles());
            }
        }
        return null;
    }

    public UserDTO createUser(String username, String password, String email, String fullname, List<String> roles){
        Date registrationDate = new Date();
        User user;
        if (username != null && password != null  && email != null && fullname != null && registrationDate != null){
            user = userService.createUser(username, password, email, fullname, registrationDate, roles);
            if (user != null){
                return new UserDTO(user.getUsername(), user.getEmail(), user.getFullName(), user.getRoles());
            }
        }
        return null;
    }

}
