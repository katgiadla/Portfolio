package com.tchorek.jobofertscollector.data;

import javax.persistence.*;

@Entity
@Table(name = "Cities")
public class Localization {
    @Id
    @Column(name = "City", nullable = false)
    private String city = "Unknown";

    public String getCity() {
        return city;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_name")
    private Job_Table job_table_name;

    public Job_Table getJob_table_name() {
        return job_table_name;
    }
}
