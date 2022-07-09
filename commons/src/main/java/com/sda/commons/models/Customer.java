package com.sda.commons.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String surname;

    @Column(nullable = false, length = 13)
    private String pesel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Account> accounts;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id)
                && Objects.equals(name, customer.name)
                && Objects.equals(surname, customer.surname)
                && Objects.equals(pesel, customer.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, pesel);
    }
}
