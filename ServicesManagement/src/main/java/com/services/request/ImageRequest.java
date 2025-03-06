package com.services.request;

import lombok.Data;

@Data
public class ImageRequest {

	private String imageName;
	private byte[] imageData;
}
