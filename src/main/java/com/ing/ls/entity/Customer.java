package com.ing.ls.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

    @Column(name = "name")
    private String customerName;

    @OneToMany(targetEntity = Loan.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="customer_id_fk",referencedColumnName = "customerId")
    private List<Loan> loans;

    public Customer addLoans(List<Loan> loans) {
        this.getLoans().addAll(loans);
        return this;

    }
}
