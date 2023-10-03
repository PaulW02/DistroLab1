package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    User login(String username, String password);
    User createUser(String username, String password, String fullname, String email, Date registrationDate, List<String> roles);
}
