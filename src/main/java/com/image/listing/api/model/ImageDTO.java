package com.image.listing.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;;

/**
 * Represent a image object returned from webservice call
 * 
 * @author johnjia
 *
 */
@JsonInclude(Include.NON_NULL)
public class ImageDTO {
	
	private String name;
	private String url;
	
	public String getName() {
		return name;
	}
	
	public String getImageUrl() {
		return url;
	}
	
	public ImageDTO(String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "Image is name: " + name + ", url: " + url;
	}
}
