package kth.distrolab1.bo.controllers;

import kth.distrolab1.bo.entities.User;
import kth.distrolab1.bo.services.UserService;
import kth.distrolab1.bo.services.UserServiceImpl;
import kth.distrolab1.ui.dtos.UserDTO;

import java.util.Date;
import java.util.List;

/**
 * The UserController class provides methods to manage user-related operations.
 * It acts as an intermediary between the view and the service layer, handling
 * user-related requests and operations.
 */
public class UserController {

    private UserService userService = new UserServiceImpl();

    /**
     * Authenticates a user based on the provided username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return UserDTO representing the authenticated user or null if authentication failed.
     */
    public UserDTO login(String username, String password) {
        if (username != null && password != null) {
            return userService.login(username, password);
        }
        return null;
    }

    /**
     * Creates a new user with the provided details.
     *
     * @param username The username of the new user.
     * @param password The password of the new user.
     * @param email The email address of the new user.
     * @param fullname The full name of the new user.
     * @param roles The roles assigned to the new user.
     * @return UserDTO representing the created user or null if creation failed.
     */
    public UserDTO createUser(String username, String password, String email, String fullname, List<String> roles){
        if (username != null && password != null  && email != null && fullname != null){
            return userService.createUser(username, password, email, fullname, roles);
        }
        return null;
    }

    public boolean deleteUser(int userId) {
        if (userId > 0){
            return userService.deleteUser(userId);
        }
        return false;
    }

    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }
}