package com.samson.exceptions;

public class ImageUploadException extends RuntimeException{
	 
	public ImageUploadException(String msg){
		super(msg);
	}
	
	public ImageUploadException(String msg, Exception e){
		super(msg, e);
	}
}
