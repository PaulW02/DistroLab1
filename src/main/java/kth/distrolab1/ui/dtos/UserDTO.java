package kth.distrolab1.ui.dtos;

import java.util.List;

public class UserDTO {


    private int id;
    private String username;
    private String email;

    private String fullname;

    private List<String> roles;


    public UserDTO(int id, String username, String email, String fullname, List<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullname = fullname;
        this.roles = roles;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
