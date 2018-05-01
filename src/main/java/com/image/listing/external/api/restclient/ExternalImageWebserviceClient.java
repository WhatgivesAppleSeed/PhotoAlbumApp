package com.image.listing.external.api.restclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.image.listing.external.api.model.ExternalImageResultDTO;

/**
 * External rest template definition
 * 
 * @author johnjia
 */
@Service
public class ExternalImageWebserviceClient {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExternalImageWebserviceClient.class);
	
	final String remote_uri = "http://5ad8d1c9dc1baa0014c60c51.mockapi.io/api/br/v1/magic";
	
	RestTemplate thirdPartyRestTemplate = new RestTemplate();
	
	/**
	 * Retrieving single image by id from external webservice
	 * 
	 * @param id id of the image that will be retrieved
	 * @return null if nothing is found, else return the image object
	 */
	public ExternalImageResultDTO getImage(String id) {
		
		ResponseEntity<ExternalImageResultDTO> resp = null;
		try {
			String url = remote_uri + "/" + id;
			
			LOG.debug("Making webservice call to: " + url);
			resp = thirdPartyRestTemplate.getForEntity(url, ExternalImageResultDTO.class);
		} catch (RestClientException e) {
			LOG.error("Error: " + e);
		}

		//TODO: Better handling than null
		if (resp == null) return null;
		return resp.getBody();
	}

}
