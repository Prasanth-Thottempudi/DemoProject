package com.fooddelivery.services.impl;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fooddelivery.dao.RecipeDao;
import com.fooddelivery.entity.Recipe;
import com.fooddelivery.request.recipeRequest;
import com.fooddelivery.response.MinioServiceResponse;
import com.fooddelivery.response.ReciepeResponse;
import com.fooddelivery.services.MinioServices;
import com.fooddelivery.services.RecipeService;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

@Service
public class RecipeServiceImpl implements RecipeService {
	
	@Autowired
	private RecipeDao recipeDao;
	
	@Autowired
	private MinioServices minioServices;

	@Override
	public ReciepeResponse addRecipe(recipeRequest request, MultipartFile file) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException {
		ReciepeResponse response=new ReciepeResponse();
		Recipe recipe=new Recipe();
		BeanUtils.copyProperties(request, recipe);
		recipe.setCreatedDate(LocalDateTime.now());
		String fileName = file.getOriginalFilename();
		minioServices.saveImage(file, fileName);
		recipe.setRecipeImageUrl(fileName);
		Recipe savedRecipe = recipeDao.save(recipe);
		response.setReciepeId(savedRecipe.getRecipeId());
		response.setResponseMessage("recipe saved successfully");
		response.setResponseStatus("1");
		return response;
	}

	@Override
	public List<ReciepeResponse> getAllServicesByBusinessId(String businessId) {
		 List<ReciepeResponse> response=new ArrayList<>();
		 List<Recipe> allRecipes = recipeDao.findAll();
		 response=allRecipes.stream()
		     .filter(i -> businessId.equals(i.getBusinessId().toString()))  // Assuming businessId is a String
		     .map(i -> {
		         ReciepeResponse res = new ReciepeResponse();
		         res.setReciepeId(i.getRecipeId());
		         
		         try {
					MinioServiceResponse imageUrl = minioServices.getImageUrl(i.getRecipeImageUrl());
			         res.setRecipeUrl(imageUrl.getImageUrl());

				} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
						| InvalidResponseException | NoSuchAlgorithmException | XmlParserException | ServerException
						| IllegalArgumentException | IOException e) {
					e.printStackTrace();
				}
		         res.setRecipeName(i.getRecipeId());  // Assuming recipe name is not provided, setting recipeId instead
		         res.setRecipeDescription(i.getRecipeDescription());
		         res.setRecipePrice(i.getRecipePrice());
		         res.setBusinessId(i.getBusinessId());
		         return res;
		     })
		     .collect(Collectors.toList());
		 
		 return response;

	}
	

}
