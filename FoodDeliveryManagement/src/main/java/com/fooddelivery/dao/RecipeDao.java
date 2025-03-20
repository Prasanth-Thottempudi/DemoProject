package com.fooddelivery.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.entity.Recipe;

public interface RecipeDao  extends JpaRepository<Recipe, String> {

}
