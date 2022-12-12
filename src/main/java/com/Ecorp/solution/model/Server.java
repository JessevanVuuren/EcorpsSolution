package com.Ecorp.solution.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "server")
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private int ram;
    private int cpu;
    private int space;
    private String stype;

    public Server(String name, double price, int ram, int cpu, int space, String stype) {
        this.name = name;
        this.price = price;
        this.ram = ram;
        this.cpu = cpu;
        this.space = space;
        this.stype = stype;
    }
}
