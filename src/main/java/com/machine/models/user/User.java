package com.machine.models.user;

import com.machine.models.Entity;
import com.machine.models.user.role.Role;

public class User implements Entity {
    private long id;
    private int UserRoleId;
    private String username;
    private String password;
    private String fullName;
    private Role role;

    public User() {
    }

    public User(int userRoleId, String username, String password, String fullName, Role role) {
        UserRoleId = userRoleId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    public User(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserRoleId() {
        return UserRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        UserRoleId = userRoleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", UserRoleId=" + UserRoleId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", role=" + role +
                '}';
    }
}
