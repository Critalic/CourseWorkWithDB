package com.example.CourseWorkWithDB.Model.JPA;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "lot_offer")
@Getter
@Setter
@Entity
public class LotOffer {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String description;
    @Column(name = "suggested_price")
    private double suggestedPrice;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer offerer;

    @ManyToOne
    @JoinColumn(name = "lot_id", referencedColumnName = "id")
    private Lot lot;

    @Column(name = "create_time")
    private Instant createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
