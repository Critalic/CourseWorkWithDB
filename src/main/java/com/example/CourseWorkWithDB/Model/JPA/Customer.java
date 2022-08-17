package com.example.CourseWorkWithDB.Model.JPA;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;
    @Column(name = "username")
    private String name;
    private String email;
    private String password;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer")
    private List<Lot> lots;

    @ToString.Exclude
    @OneToOne(mappedBy = "offerer")
    private LotOffer offer;
}
