package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.User;
import kth.distrolab1.db.repositories.UserRepository;
import kth.distrolab1.db.repositories.UserRepositoryImpl;
import kth.distrolab1.ui.dtos.UserDTO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class UserServiceImpl implements UserService{

    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public UserDTO login(String username, String password) {
        if (username != null && password != null) {
            String encryptedPassword = encrypt(password);
            User user = userRepository.findByUsernameAndPassword(username, encryptedPassword);
            if (user != null) {
                return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getFullName(), user.getRoles());
            }
        }
        return null;
    }

    @Override
    public UserDTO createUser(String username, String password, String fullname, String email, List<String> roles) {
        String encryptedPassword = encrypt(password);
        Date registrationDate = new Date();
        User user;
        if (username != null && password != null  && email != null && fullname != null && registrationDate != null){
            if (roles.isEmpty()){
                roles.add("role_user");
            }
            user = userRepository.createUser(username,encryptedPassword,fullname,email,registrationDate, roles);
            if (user != null){
                return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getFullName(), user.getRoles());
            }
        }
        return null;
    }

    @Override
    public boolean deleteUser(int userId) {
        return userRepository.deleteUserById(userId);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRepository.getAllUsers();
        for (User user: users) {
            userDTOS.add(new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getFullName(), user.getRoles()));
        }
        return userDTOS;
    }

    public static String encrypt(String input) {
        try {
            // Create a MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Update the digest with the byte representation of the input string
            md.update(input.getBytes());

            // Compute the MD5 hash
            byte[] digest = md.digest();

            // Convert the byte array to a hexadecimal representation
            StringBuilder result = new StringBuilder();
            for (byte b : digest) {
                result.append(String.format("%02x", b));
            }

            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle any exceptions that might occur
            e.printStackTrace();
            return null;
        }
    }
}
