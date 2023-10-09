package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.User;
import kth.distrolab1.ui.dtos.UserDTO;

import java.util.Date;
import java.util.List;

public interface UserService {

    UserDTO login(String username, String password);
    UserDTO createUser(String username, String password, String fullname, String email, List<String> roles);

    boolean deleteUser(int userId);

    List<UserDTO> getAllUsers();
}
