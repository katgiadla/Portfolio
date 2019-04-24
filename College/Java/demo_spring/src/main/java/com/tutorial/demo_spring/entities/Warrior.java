package com.tutorial.demo_spring.entities;

import javax.persistence.*;

@Entity
@Table(name = "Warrior class")
public class Warrior {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


}
