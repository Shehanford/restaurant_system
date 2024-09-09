package com.mycompany.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_inquiries")
public class UserInquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 1000)
    private String query;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "UserInquiry{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", query='" + query + '\'' +
                '}';
    }
}