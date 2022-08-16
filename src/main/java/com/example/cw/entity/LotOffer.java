package com.example.cw.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "lot_offer")
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
public class LotOffer implements BasicEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    @Column(name = "suggested_price")
    @NonNull
    private Double suggestedPrice;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer offerer;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "lot_id", referencedColumnName = "id")
    private Lot lot;

    @Column(name = "create_time", insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTime;

    public LotOffer setId(Long id) {
        this.id = id;
        return this;
    }

    public LotOffer setDescription(String description) {
        this.description = description;
        return this;
    }

    public LotOffer setSuggestedPrice(double suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
        return this;
    }

    public LotOffer setOfferer(Customer offerer) {
        this.offerer = offerer;
        return this;
    }

    public LotOffer setLot(Lot lot) {
        this.lot = lot;
        return this;
    }

    public LotOffer setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }
}
