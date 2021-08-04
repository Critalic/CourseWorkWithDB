package com.example.CourseWorkWithDB.Model;

import java.sql.Timestamp;

public class LotOffer {
    private long id;
    private long owner_id;
    private long lot_id;
    private String description;
    private int cost;
    private Timestamp create_time;
    private Lot lot;
    private User owner;

    public LotOffer(long owner_id, long lot_id, String description, int cost) {
        this.owner_id = owner_id;
        this.lot_id = lot_id;
        this.description = description;
        this.cost = cost;
    }

    public LotOffer(long owner_id, long lot_id, int cost) {
        this.owner_id = owner_id;
        this.lot_id = lot_id;
        this.description = description;
        this.cost = cost;
    }

    public LotOffer( String description, int cost) {
        this.description = description;
        this.cost = cost;
    }


    public Lot getLot() {
        return lot;
    }

    public User getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public int getMoney() {
        return cost;
    }

    public long getId() {
        return id;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public long getLot_id() {
        return lot_id;
    }

    public int getCost() {
        return cost;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public void setLot_id(long lot_id) {
        this.lot_id = lot_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LotOffer)) return false;

        LotOffer offer = (LotOffer) o;

        if (getMoney() != offer.getMoney()) return false;
        if (!getOwner().equals(offer.getOwner())) return false;
        if (!getDescription().equals(offer.getDescription())) return false;
        return getLot().equals(offer.getLot());
    }
}
