package com.epam.onlinestore.entity;

import java.time.OffsetDateTime;
import java.util.Objects;

public class Receipt {

    private long id;
    private String description;
    private long total;
    private OffsetDateTime create_date;

    public Receipt() {
    }

    public Receipt(long id, String description, long total, OffsetDateTime create_date) {
        this.id = id;
        this.description = description;
        this.total = total;
        this.create_date = create_date;
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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public OffsetDateTime getCreate_date() {
        return create_date;
    }

    public void setCreate_date(OffsetDateTime create_date) {
        this.create_date = create_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receipt)) return false;
        Receipt receipt = (Receipt) o;
        return getId() == receipt.getId() && getTotal() == receipt.getTotal()
                && Objects.equals(getDescription(), receipt.getDescription())
                && Objects.equals(getCreate_date(), receipt.getCreate_date());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getTotal(), getCreate_date());
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", total=" + total +
                ", create_date=" + create_date +
                '}';
    }
}
