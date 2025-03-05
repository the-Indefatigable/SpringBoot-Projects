package com.alam.CompanyMicroservice.company;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    private Double rating;


//    @OneToMany(mappedBy = "company" ,cascade = CascadeType.ALL, orphanRemoval = true)
    //above one can work if you dont want to write the whole logic for deletion


    public Company(Long id, String description, String name  ) {
        this.id = id;
        this.description = description;
        this.name = name;

    }

    public Company() {
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
