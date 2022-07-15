package com.example.CourseWorkWithDB.Entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "lot")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Lot implements BasicEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;
    private String description;
    @Column(name = "start_price")
    @NonNull
    private Double startPrice;
    @Column(name = "is_active")
    @NonNull
    private Boolean isActive;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "lot")
    @ToString.Exclude
    private List<LotOffer> offers;

    @Column(name = "create_time", insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTime;

    public Lot setId(Long id) {
        this.id = id;
        return this;
    }

    public Lot setName(String name) {
        this.name = name;
        return this;
    }

    public Lot setDescription(String description) {
        this.description = description;
        return this;
    }

    public Lot setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
        return this;
    }

    public Lot setActive(boolean active) {
        isActive = active;
        return this;
    }

    public Lot setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Lot setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }
}
