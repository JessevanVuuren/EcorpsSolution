package com.Ecorp.solution.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "orders")

public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String server;
    private Long userid;
    private int amount;

    public Orders(String server, Long userid, int amount) {
        this.userid = userid;
        this.server = server;
        this.amount = amount;
    }
}
