package com.example.CourseWorkWithDB.Model;

import java.sql.Timestamp;
import java.util.List;

public class Lot {
    private long id;
    private long ownerId;
    private String description;
    private int startPrice;
    private String name;
    private boolean isActive;
    private Timestamp create_time;
    private User owner;

    public Lot(long id, long ownerId, String description, String name, int startPrice , boolean isActive) {
        this.ownerId = ownerId;
        this.description = description;
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.startPrice = startPrice;
    }

    public Lot(String name,String description , int startPrice, long ownerId) {
        this.description = description;
        this.name = name;
        this.ownerId = ownerId;
        this.startPrice = startPrice;
    }

    public User getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setStatus(boolean value) { isActive = value;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lot)) return false;

        Lot lot = (Lot) o;

        return getId() == lot.getId();
    }
}
