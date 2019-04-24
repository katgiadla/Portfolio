package com.tchorek.jobofertscollector.data;

import lombok.Data;

import javax.persistence.*;
import java.sql.*;

@Data
@Entity
@Table(name = "jobtable")
public class Job_Table {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("job-oferts-collector");
        EntityManager em = emf.createEntityManager();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oferts", "root", "admin");
            Statement statement = connection.createStatement();
            em.getTransaction().begin();

            Job_Table job_table = new Job_Table("ddd", "this is funny", true);
            Localization localization = new Localization();

            //statement.execute();
            //statement.executeUpdate("INSERT INTO oferty(job_name, description, interesting) VALUES ('StringFromCode','This record comes from java connection, statement and resultset',TRUE )");

            em.persist(job_table);
            em.persist(localization);
            //em.flush();
            em.getTransaction().commit();
            em.close();
            emf.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @OneToOne(mappedBy = "job_table_name", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Localization localization;

    public Localization getLocalization() {
        return localization;
    }

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //MANUAL ID INSERTION
    @Column(name = "job_id", nullable = false)
    private int job_id = 5;

    @Column(name = "title", nullable = false)
    private String job_title;

    @Column(name = "description", nullable = false)
    private String job_description;

    @Column(name = "interesting", nullable = false)
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

    public Job_Table(int id, String job_title, String job_description, boolean interesting) {
        this.job_id = id;
        this.job_title = job_title;
        this.job_description = job_description;
        this.interesting = interesting;
    }

    public Job_Table(String job_title, String job_description, boolean interesting) {
        this.job_title = job_title;
        this.job_description = job_description;
        this.interesting = interesting;
    }
}
