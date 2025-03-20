package com.fooddelivery.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fooddelivery.request.recipeRequest;
import com.fooddelivery.response.ReciepeResponse;
import com.fooddelivery.response.Response;
import com.fooddelivery.services.RecipeService;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

@RestController
@RequestMapping("/food-delivery")
public class ReciepeController {
	
	@Autowired
	private RecipeService recipeService;
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "food delivery dashboard";
	}
	@PostMapping("/add-recipe")
	public ResponseEntity<ReciepeResponse> addReciepe(@RequestParam("file") MultipartFile file,@RequestParam("data") String data){
		ObjectMapper objectMapper = new ObjectMapper();
		ReciepeResponse res=new ReciepeResponse();
		try {
			recipeRequest request = objectMapper.readValue(data, recipeRequest.class);
			 res=		recipeService.addRecipe(request,file);
		} catch (IOException | InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException | InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(res);
	}
	
	@GetMapping("/get-all-recipes/{businessId}")
	public ResponseEntity<List<ReciepeResponse>> getAllRecipesByBusinessId(@PathVariable String businessId){
		List<ReciepeResponse> res=recipeService.getAllServicesByBusinessId(businessId);
		return ResponseEntity.ok(res);
	}
}
