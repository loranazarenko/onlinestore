package com.epam.onlinestore.entity;

public class Status {
    private long id;
    private String statusName;


    public Status() {
    }

    public Status(long id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Status category = (Status) o;
        return id == category.id &&
                statusName.equals(category.statusName);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) id;
        result = prime * result + ((statusName == null) ? 0 : statusName.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Status{" + "id=" + id +
                ", categoryName='" + statusName + '\'' +
                '}';
    }
}
