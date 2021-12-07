package com.epam.onlinestore.entity;

import java.util.Objects;

public class Receipt {

    private long id;
    private String description;
    private double total;
    private long statusId;
    private long accountId;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    public Receipt() {
    }

    public Receipt(long id, String description, double total, long statusId, long accountId) {
        this.id = id;
        this.description = description;
        this.total = total;
        this.statusId = statusId;
        this.accountId = accountId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receipt)) return false;
        Receipt receipt = (Receipt) o;
        return getId() == receipt.getId() && getTotal() == receipt.getTotal()
                && Objects.equals(getDescription(), receipt.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getTotal());
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", total=" + total +
                '}';
    }
}
