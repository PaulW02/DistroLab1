package kth.distrolab1.db.repositories;

import kth.distrolab1.bo.entities.Role;
import kth.distrolab1.bo.entities.User;

import java.util.Date;
import java.util.List;

public interface UserRepository {

    User findByUsernameAndPassword(String username, String password);
    User createUser(String username, String password, String email, String fullname, java.util.Date registrationDate, List<Role> roles);
}
