package com.mycompany.model;

import jakarta.persistence.*;

@Entity
@Table(name = "queries")
public class Queries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false)
    private String firstName;

    @Column(length = 45, nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(length = 250, nullable = false)
    private String queries;

    // Getters
    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getQueries() {
        return queries;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setQueries(String queries) {
        this.queries = queries;
    }

    @Override
    public String toString() {
        return "Queries{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", queries='" + queries + '\'' +
                '}';
    }
}
