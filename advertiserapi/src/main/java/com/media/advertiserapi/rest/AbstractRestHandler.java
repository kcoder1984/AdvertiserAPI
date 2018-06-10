package com.media.advertiserapi.rest;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.media.advertiserapi.exception.DataFormatException;
import com.media.advertiserapi.exception.ResourceNotFoundException;
import com.media.advertiserapi.model.RestError;


public class AbstractRestHandler extends SpringBootServletInitializer {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataFormatException.class)
    public @ResponseBody
    RestError handleDataStoreException(DataFormatException ex, WebRequest request, HttpServletResponse response) {
        log.info("Converting Data Store exception to RestResponse : " + ex.getMessage());
        return new RestError(ex, "Something wrong with the data.");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public
    @ResponseBody
    RestError handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request, HttpServletResponse response) {
        log.info("ResourceNotFoundException handler:" + ex.getMessage());
        return new RestError(ex, "Sorry couldn't find it.");
    }
    
    public static <T> T checkResourceFound(final Optional<T> resource) {
		if (!resource.isPresent()) {
			throw new ResourceNotFoundException("resource not found");
		}
		return resource.get();
	}
}
