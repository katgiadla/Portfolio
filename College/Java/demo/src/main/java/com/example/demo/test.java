package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name = "Warrior")
public class test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


}
