package com.fusionhub.reciperealm.webservices.models;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = true, columnDefinition = "MEDIUMTEXT")
    private String description; 

    private String cookTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", orphanRemoval = true)
    private List<RecipeSteps> steps;

    @Column(nullable = true, columnDefinition = "MEDIUMTEXT")
    private String videoLink; 
    
    @Column(nullable = true,columnDefinition = "MEDIUMTEXT")
    private String imageLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String ingredients;

     @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeDetails> details;

    @OneToOne(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RecipeNote note;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeRating> ratings;
}
