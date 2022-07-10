package com.example.CourseWorkWithDB.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "lot_offer")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class LotOffer {
    @Id
    @Column(name = "id", nullable = false)
    @NonNull
    private Long id;

    private String description;
    @Column(name = "suggested_price")
    @NonNull
    private double suggestedPrice;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer offerer;

    @ManyToOne
    @JoinColumn(name = "lot_id", referencedColumnName = "id")
    private Lot lot;

    @Column(name = "create_time")
    private Instant createTime;
}
