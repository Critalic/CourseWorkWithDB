package com.example.CourseWorkWithDB.Model.JPA;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Table(name = "lot")
@Getter
@Setter
@Entity
public class Lot {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String description;
    @Column(name = "start_price")
    private double startPrice;
    @Column(name = "offers_count")
    private int offersCount;
    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "lot")
    private List<LotOffer> offers;

    @Column(name = "create_time")
    private Instant createTime;

    public Customer getCustomer() {
        return customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
