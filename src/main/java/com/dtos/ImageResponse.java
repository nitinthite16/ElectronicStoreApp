package com.dtos;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageResponse {

	private String imageName;
	private String message;
	private boolean success;
	private HttpStatus status;
	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
