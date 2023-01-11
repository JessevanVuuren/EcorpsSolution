package com.Ecorp.solution.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "promocode")
public class Promocode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double amountoff;

    public Promocode(String name, double amountoff) {
        this.name = name;
        this.amountoff = amountoff;
    }
}
