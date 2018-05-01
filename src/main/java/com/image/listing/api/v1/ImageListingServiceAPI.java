package com.image.listing.api.v1;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.image.listing.ImageListing;
import com.image.listing.api.model.ImageDTO;

/**
 * Controller for the image webservice api
 * 
 * @author johnjia
 *
 */
@Controller
@RequestMapping("api/v1")
public class ImageListingServiceAPI {
	
	private static final Logger LOG = LoggerFactory.getLogger(ImageListingServiceAPI.class);
	
	@Autowired
	private ImageListing imageListingService;
	
	/**
	 * List all images from external webserivce
	 * 
	 * @return List of images separated by new line
	 */
	@RequestMapping(value="images", method=RequestMethod.GET)
	public @ResponseBody List<ImageDTO> getImages() {
		LOG.info("getImages: Getting list of all images");
		
		List<ImageDTO> resultUrls = imageListingService.getImageUrlsFromExternalService();
		return resultUrls;
	}
}
