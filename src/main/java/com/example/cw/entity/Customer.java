package com.example.cw.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Table(name = "customer")
public class Customer implements BasicEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    @Column(name = "password")
    private String passwordHash;

//    @ToString.Exclude
//    @OneToMany(mappedBy = "customer")
//    private List<Lot> lots;
//
//    @ToString.Exclude
//    @OneToMany(mappedBy = "offerer")
//    private List<LotOffer> offers;

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }

    public Customer setId(Long id) {
        this.id = id;
        return this;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }
}
