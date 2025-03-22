package com.fooddelivery.services;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fooddelivery.request.recipeRequest;
import com.fooddelivery.response.ReciepeListResponse;
import com.fooddelivery.response.ReciepeResponse;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

public interface RecipeService {

	ReciepeResponse addRecipe(recipeRequest request, MultipartFile file) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException;

	List<ReciepeResponse> getAllServicesByBusinessId(String businessId);

	ReciepeListResponse getAllRecipes();
	
	

}
