package com.epam.onlinestore.entity;

import java.util.Objects;

public class AccountDetail {

    private long id;
    private long accountId;
    private String name;
    private String email;

    public AccountDetail() {
    }

    public AccountDetail(long id, long accountId, String name, String email) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.email = email;
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

    public void setAccountId(long accountId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountDetail)) return false;
        AccountDetail that = (AccountDetail) o;
        return getAccountId() == that.getAccountId() && Objects.equals(getName(), that.getName()) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountId(), getName(), getEmail());
    }

    @Override
    public String toString() {
        return "AccountDetail{" +
                "accountId=" + accountId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
