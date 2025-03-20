package com.fooddelivery.response;

import lombok.Data;

@Data
public class ReciepeResponse extends Response{

	private String reciepeId;
	private String businessId;
	private String recipeUrl;
	private String recipeName;
	private String recipeDescription;
	private Double recipePrice;

	
	
}
