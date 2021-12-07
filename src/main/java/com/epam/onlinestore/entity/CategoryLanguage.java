package com.epam.onlinestore.entity;

import java.util.Objects;

public class CategoryLanguage {

    private String name;
    private long categoryId;
    private long languageId;

    public CategoryLanguage(String name, long categoryId, long languageId) {
        this.name = name;
        this.categoryId = categoryId;
        this.languageId = languageId;
    }

    public CategoryLanguage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(long languageId) {
        this.languageId = languageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryLanguage)) return false;
        CategoryLanguage that = (CategoryLanguage) o;
        return getCategoryId() == that.getCategoryId() && getLanguageId() == that.getLanguageId() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCategoryId(), getLanguageId());
    }

    @Override
    public String toString() {
        return "CategoryLanguage{" +
                "name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", languageId=" + languageId +
                '}';
    }
}
