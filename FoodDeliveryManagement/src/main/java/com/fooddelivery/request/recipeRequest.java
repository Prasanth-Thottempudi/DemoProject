package com.fooddelivery.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class recipeRequest {

	private String businessId;

	private String recipeDescription;

	private Double recipePrice;
}
