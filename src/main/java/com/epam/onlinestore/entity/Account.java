package com.epam.onlinestore.entity;

public class Account {
    private long id;
    private String login;
    private String password;
    private int roleId;
    private long accountDetailId;
    private int statusId;

    public Account(long id, String login, String password, int statusId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.statusId = statusId;
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

    public int getUserStatuses() {
        return statusId;
    }

    public void setUserStatuses(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                ", accountDetailId=" + accountDetailId +
                ", statusId=" + statusId +
                '}';
    }
}
