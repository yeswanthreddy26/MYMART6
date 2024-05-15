package com.developtrees.reCaptcha.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.developtrees.reCaptcha.entity.ReCaptcha;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;



@Service
public class ReCaptchaValidationService {
	
	
	
	private static final String GOOGLE_RECAPTCHA_ENDPOINT = "https://www.google.com/recaptcha/api/siteverify";
    
	private final String RECAPTCHA_SECRET = "6LfAc7ApAAAAAOM42fR7HnwTvZOqPaFxgZlsInnc";
	

	    public boolean validateCaptcha(String captchaResponse){
	        RestTemplate restTemplate = new RestTemplate();

	        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
	        requestMap.add("secret", RECAPTCHA_SECRET);
	        requestMap.add("response", captchaResponse);

	       ReCaptcha apiResponse = restTemplate.postForObject(GOOGLE_RECAPTCHA_ENDPOINT, requestMap, ReCaptcha.class);
	       
	        if(apiResponse == null){
	            return false;
	        }

	        return Boolean.TRUE.equals(apiResponse.isSuccess());
	    }
	
	
	
	
	
	
    // Read secret key from environment variable 
//    private final String RECAPTCHA_SECRET = System.getenv("RECAPTCHA_SECRET");
//
//    
//    
//    /**
//     * Validates the reCAPTCHA response sent by the user.
//     *
//     * @param captchaResponse The user's reCAPTCHA response token.
//     * @return True if validation is successful, false otherwise.
//     */
//    public boolean validateCaptcha(String captchaResponse) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        Map<String, String> requestParams = new HashMap<>();
//        requestParams.put("secret", RECAPTCHA_SECRET);
//        requestParams.put("response", captchaResponse);
//
//        try {
//            // Send POST request and parse response as a map
//            Map<String, Object> responseMap = restTemplate.postForObject(Constants.GOOGLE_RECAPTCHA_ENDPOINT, requestParams, Map.class);
//
//            // Check for successful validation ("success" field)
//            return (boolean) responseMap.get("success");
//        } catch (HttpClientErrorException | HttpServerErrorException e) {
//           
//            System.err.println("Error during reCAPTCHA validation: " + e.getMessage());
//            return false;
//        } catch (Exception e) {
//            
//            System.err.println("Unexpected error during reCAPTCHA validation: " + e.getMessage());
//            return false;
//        }
//    }
//    
    
	
	
	

}
