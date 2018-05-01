package com.image.listing;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.image.listing.api.model.ImageDTO;
import com.image.listing.external.api.model.ExternalImageResultDTO;
import com.image.listing.external.api.restclient.ExternalImageWebserviceClient;

/**
 * Service method that grabs all images from external rest endpoint and return it as a list of string of URL
 * 
 * @author johnjia
 */
@Service
public class ImageListing {
	private static final Logger LOG = LoggerFactory.getLogger(ImageListing.class);

	@Autowired
	private ExternalImageWebserviceClient externalImageWebserviceClient;

	private final int PAGING_SIZE = 50;
	
	/**
	 * Fetch images while incrementing the index by paging size
	 * 
	 * @return List of urls for the corresponding images
	 */
	public List<ImageDTO> getImageUrlsFromExternalService() {
		LOG.debug("getImageUrlsFromExternalService start.");
		List<ImageDTO> images = new ArrayList<ImageDTO>();
		
		int startingIndex = 0;
		
		while(true) {
			List<ImageDTO> returnedImageUrls = getImageUrlsByPaging(startingIndex);
			if (returnedImageUrls.isEmpty()) break;
			images.addAll(returnedImageUrls);
			startingIndex = startingIndex + PAGING_SIZE;
		}
		
		LOG.debug("getImageUrlsFromExternalService end.");
		return images;
	}
	
	/**
	 * Fetch images from the URL by paging size offset by start parameter
	 * 
	 * @param start starting index of where the first image
	 * @return List of image URLs that is included between start index and paging size
	 */
	private List<ImageDTO> getImageUrlsByPaging(int start) {
		LOG.debug("getImageUrlsByPaging start at index " + start);
		
		List<ImageDTO> images = new ArrayList<ImageDTO>();
		
		for (int i = start+1; i< start+PAGING_SIZE; i++) {
			ExternalImageResultDTO imageResult = new ExternalImageResultDTO();
			imageResult = externalImageWebserviceClient.getImage(Integer.toString(i));
			if (imageResult != null) {
				LOG.debug("getImageUrlsByPaging: image url added for index:" + i + " with urls: " + imageResult.getImageUrl());
				images.add(new ImageDTO(imageResult.getName(), imageResult.getImageUrl()));
			}
		}
		LOG.debug("getImageUrlsByPaging end");
		return images;
	}
}
