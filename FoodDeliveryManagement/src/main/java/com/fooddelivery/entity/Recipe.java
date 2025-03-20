package com.fooddelivery.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="recipe_data")
public class Recipe {

	@Id
	@Column(name="recipe_id")
	@GeneratedValue(generator = "recipe-id-generator")
    @GenericGenerator(
        name = "recipe-id-generator", 
        strategy = "com.fooddelivery.idgenerator.RecipeIdGenerator"  // Ensure this is the correct fully qualified path
    )
    private String recipeId;

    private String businessId;

    private String recipeDescription;

    private Double recipePrice;

    private String recipeImageUrl;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
