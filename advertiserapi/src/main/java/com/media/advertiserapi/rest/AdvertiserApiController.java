package com.media.advertiserapi.rest;

import java.math.BigDecimal;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.media.advertiserapi.exception.DataFormatException;
import com.media.advertiserapi.model.Advertiser;
import com.media.advertiserapi.service.AdvertiserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/advertiser")
@Api(tags = {"Advertiser"})
public class AdvertiserApiController extends AbstractRestHandler {

    private static final Logger LOG = LoggerFactory.getLogger(AdvertiserApiController.class);

    @Autowired
    AdvertiserService advertiserService;

    @RequestMapping(value = "", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a Advertiser resource.", notes = "Returns the URL of the new resource in the Location header.")
    public Advertiser createAdvertiser(@RequestBody Advertiser advertiser) {
        Advertiser createdAdvertiser = advertiserService.createAdvertiser(advertiser);
        return createdAdvertiser;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get single advertiser.", notes = "You have to provide a valid advertiser ID.")
    @ResponseBody
    public Advertiser getAdvertiser(@ApiParam(value = "The ID of Advertiser.", required = true) @PathVariable("id") Long id, HttpServletResponse response) {

    	if (LOG.isInfoEnabled()) {
			LOG.info("Get Advertiser Called for Id : {}", id);
		}

        Optional<Advertiser> advertiser = this.advertiserService.getAdvertiser(id);

        checkResourceFound(advertiser);

        return this.advertiserService.getAdvertiser(id).get();
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Update a advertiser resource.", notes = "You have to provide a valid advertiser ID in the URL and in the payload. The ID attribute can not be updated.")
    public void updateAdvertiser(@ApiParam(value = "The ID of the existing advertiser resource.", required = true) @PathVariable("id") Long id, @RequestBody Advertiser advertiser) {
		if (LOG.isInfoEnabled()) {
			LOG.info("Update Advertiser Called for Id : {}", id);
		}
        checkResourceFound(this.advertiserService.getAdvertiser(id));
        if (id != advertiser.getId()) throw new DataFormatException("ID doesn't match");

        this.advertiserService.updateAdvertiser(advertiser);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete a advertiser resource.", notes = "You have to provide a valid advertiser ID in the URL. Once deleted the resource can not be recovered.")
    public void deleteAdvertiser(@ApiParam(value = "The ID of the existing advertiser resource.", required = true) @PathVariable("id") Long id) {
		if (LOG.isInfoEnabled()) {
			LOG.info("Delete Advertiser Called for Id : {}", id);
		}
        checkResourceFound(this.advertiserService.getAdvertiser(id));
        this.advertiserService.deleteAdvertiser(id);
    }
    

}
