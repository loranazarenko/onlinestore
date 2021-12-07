package com.epam.onlinestore.entity;

public class Account {
    private long id;
    private String login;
    private String password;
    private int roleId;
    private long accountDetailId;

    public Account(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Account() {
    }

    public long getAccountDetailId() {
        return accountDetailId;
    }

    public void setAccountDetailId(long accountDetailId) {
        this.accountDetailId = accountDetailId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" + "user id=" + getId() +
                "login='" + login + '\'' +
                ", roleId=" + roleId +
                '}';
    }


}
