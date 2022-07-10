package com.example.CourseWorkWithDB.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Table(name = "lot")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Lot {
    @Id
    @Column(name = "id", nullable = false)
    @NonNull
    private Long id;

    @NonNull
    private String name;
    private String description;
    @Column(name = "start_price")
    @NonNull
    private double startPrice;
    @Column(name = "offers_count")
    @NonNull
    private int offersCount;
    @Column(name = "is_active")
    @NonNull
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "lot")
    private List<LotOffer> offers;

    @Column(name = "create_time")
    private Instant createTime;
}
