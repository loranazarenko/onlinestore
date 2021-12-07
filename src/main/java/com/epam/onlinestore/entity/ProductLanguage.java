package com.epam.onlinestore.entity;

import java.util.Objects;

public class ProductLanguage {

    private String description;
    private long productId;
    private long languageId;

    public ProductLanguage(String description, long productId, long languageId) {
        this.description = description;
        this.productId = productId;
        this.languageId = languageId;
    }

    public ProductLanguage() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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
        if (!(o instanceof ProductLanguage)) return false;
        ProductLanguage that = (ProductLanguage) o;
        return getProductId() == that.getProductId() && getLanguageId() == that.getLanguageId() && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getProductId(), getLanguageId());
    }

    @Override
    public String toString() {
        return "ProductLanguage{" +
                "description='" + description + '\'' +
                ", productId=" + productId +
                ", languageId=" + languageId +
                '}';
    }
}
