package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.Role;
import kth.distrolab1.bo.entities.User;
import kth.distrolab1.db.repositories.UserRepository;
import kth.distrolab1.db.repositories.UserRepositoryImpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService{

    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public User login(String username, String password) {
        String encryptedPassword = encrypt(password);
        return userRepository.findByUsernameAndPassword(username, encryptedPassword);
    }

    @Override
    public User createUser(String username, String password, String fullname, String email, Date registrationDate, List<Role> roles) {
        String encryptedPassword = encrypt(password);
        return userRepository.createUser(username,encryptedPassword,fullname,email,registrationDate, roles);
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
