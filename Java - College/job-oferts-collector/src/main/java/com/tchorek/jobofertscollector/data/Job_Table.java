package com.tchorek.jobofertscollector.data;

import lombok.Data;

import javax.persistence.*;
import java.sql.*;

@Data
@Entity
@Table(name = "joboferts")
public class Job_Table {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oferts","oferts","");
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO joboferts(id, title, description, interesting) VALUES (20,'StringFromCode','This record comes from java connection, statement and resultset',TRUE )");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int job_id;

    @Column(name="title")
    private String job_title;

    @Column(name="description")
    private String job_description;

    @Column(name="interesting")
    private boolean interesting;

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public boolean isInteresting() {
        return interesting;
    }

    public void setInteresting(boolean interesting) {
        this.interesting = interesting;
    }

    public Job_Table(String job_title, String job_description, boolean interesting) {
        this.job_title = job_title;
        this.job_description = job_description;
        this.interesting = interesting;
    }
}
