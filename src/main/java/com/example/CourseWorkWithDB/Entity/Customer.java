package com.example.CourseWorkWithDB.Entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Table(name = "customer")
public class Customer {
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
    private String password;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer")
    private List<Lot> lots;

    @ToString.Exclude
    @OneToMany(mappedBy = "offerer")
    private List<LotOffer> offers;
}
