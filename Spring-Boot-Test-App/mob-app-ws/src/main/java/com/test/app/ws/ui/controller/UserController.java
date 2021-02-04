package com.test.app.ws.ui.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.app.ws.ui.model.request.UserDetailsRequestModel;
import com.test.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {

	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue="1") int page
			, @RequestParam(value="limit", defaultValue="50") int limit
			, @RequestParam(value="sort", defaultValue="desc", required=false) String sort)
	{
		//if(sort == null) sort="desc";
		return "get users was called, page = "+page+", limit = "+limit+", sort = "+sort;
	}
	
	@GetMapping(path="/{userId}", 
			produces = { 
					MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE 
					})
	public ResponseEntity<UserRest> getUsers(@PathVariable String userId)
	{
		UserRest returnValue=new UserRest();
		returnValue.setEmail("test@test.com");
		returnValue.setFirstName("First Name");
		returnValue.setLastName("Last Name");
		//returnValue.setUserId(userId);
		
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
		//return new ResponseEntity<UserRest>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(consumes = { 
			MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE 
			}, 
			produces = { 
					MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE 
					})
	public ResponseEntity<UserRest> createUser(@RequestBody UserDetailsRequestModel userDetails)
	{
		UserRest returnValue=new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstname());
		returnValue.setLastName(userDetails.getLastname());
		
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}
	
	@PutMapping
	public String updateUser()
	{
		return "update user was called..";
	}
	
	@DeleteMapping
	public String deleteUser()
	{
		return "delete user was called..";
	}
	
}
