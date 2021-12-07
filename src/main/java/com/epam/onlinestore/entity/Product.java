package com.epam.onlinestore.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Product implements Serializable {
    private long id;
    private String name;
    private String description;
    private double price;
    private Date create_date;
    private long categoryId;
    private String color;
    private String picture;
    private int count;
    boolean is_deleted;

    public Product() {
    }

    public Product(long id, String name, String description, double price,
                   Date create_date, long categoryId, String color, String picture, int count, boolean is_deleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.create_date = create_date;
        this.categoryId = categoryId;
        this.color = color;
        this.picture = picture;
        this.count = count;
        this.is_deleted = is_deleted;
    }

    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(int id, String name, int price, String picture) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.picture = picture;
    }

    public boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return id == product.id &&
                categoryId == product.categoryId &&
                name.equals(product.name) &&
                Objects.equals(description, product.description) &&
                price == product.price &&
                Objects.equals(color, product.color);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) id;
        result = prime * result + (int) categoryId;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + (int) price;
        result = prime * result + color.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", color=" + color +
                '}';
    }
}