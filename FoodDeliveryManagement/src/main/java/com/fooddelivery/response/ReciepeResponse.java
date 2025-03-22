package com.fooddelivery.response;

import lombok.Data;

@Data
public class ReciepeResponse {

	private String responseMessage;
	private String responseStatus;
	private String reciepeId;
	private String businessId;
	private String recipeUrl;
	private String recipeName;
	private String recipeDescription;
	private Double recipePrice;

	
	
}
