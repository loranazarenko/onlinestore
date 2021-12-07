package com.epam.onlinestore.entity;

import java.util.Objects;

public class AccountDetail {

    private long id;
    private int accountId;
    private String name;
    private String email;
    private int phone;

    public AccountDetail() {
    }

    public AccountDetail(long id, int accountId, String name, String email, int phone) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountDetail)) return false;
        AccountDetail that = (AccountDetail) o;
        return getAccountId() == that.getAccountId() && getPhone() == that.getPhone() && Objects.equals(getName(), that.getName()) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountId(), getName(), getEmail(), getPhone());
    }

    @Override
    public String toString() {
        return "AccountDetail{" +
                "id=" + accountId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
    }
}
