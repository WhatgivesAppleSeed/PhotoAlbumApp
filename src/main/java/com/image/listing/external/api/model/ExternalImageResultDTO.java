package com.image.listing.external.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;;

/**
 * Represent a image object returned from webservice call
 * 
 * @author johnjia
 *
 */
@JsonInclude(Include.NON_NULL)
public class ExternalImageResultDTO {
	
	private int id;
	private String name;
	private int createDate;
	private String imageUrl;
	
	public int getId() {
		return id;
	}
	
	public int getCreateDate() {
		return createDate;
	}
	
	public String getName() {
		return name;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	

	@Override
	public String toString() {
		return "Image is ID: " + id + ", name: " + name + ", createDate: " + createDate + ", imageUrl: " + imageUrl;
	}
}
