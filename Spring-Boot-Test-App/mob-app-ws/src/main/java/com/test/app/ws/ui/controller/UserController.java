package com.test.app.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

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

import com.test.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.test.app.ws.ui.model.request.UserDetailsRequestModel;
import com.test.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {
	
	Map<String, UserRest> users;

	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue="1") int page
			, @RequestParam(value="limit", defaultValue="50") int limit
			, @RequestParam(value="sort", defaultValue="desc", required=false) String sort)
	{
		//if(sort == null) sort="desc";
		return "get users was called, page = "+page+", limit = "+limit+", sort = "+sort;
	}
	
//	@GetMapping(path="/{userId}", 
//			produces = { 
//					MediaType.APPLICATION_JSON_VALUE, 
//					MediaType.APPLICATION_XML_VALUE 
//					})
//	public ResponseEntity<UserRest> getUsers(@PathVariable String userId)
//	{
//		UserRest returnValue=new UserRest();
//		returnValue.setEmail("test@test.com");
//		returnValue.setFirstName("First Name");
//		returnValue.setLastName("Last Name");
//		//returnValue.setUserId(userId);
//		
//		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
//		//return new ResponseEntity<UserRest>(HttpStatus.BAD_REQUEST);
//	}
	
	@GetMapping(path="/{userId}", 
	produces = { 
			MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE 
			})
	public ResponseEntity<UserRest> getUsers(@PathVariable String userId)
	{
		if(users.containsKey(userId))
		{
			return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping(consumes = { 
			MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE 
			}, 
			produces = { 
					MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE 
					})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails)
	{
		UserRest returnValue=new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstname());
		returnValue.setLastName(userDetails.getLastname());
		
		String userId = UUID.randomUUID().toString();
		returnValue.setUserId(userId);
		
		if(users == null) users = new HashMap<>();
		users.put(userId, returnValue);
		
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}
	
	@PutMapping(path="/{userId}", consumes = { 
			MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE 
			}, 
			produces = { 
					MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE 
					})
	public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails)
	{
		UserRest storedUserDetails = users.get(userId);
		storedUserDetails.setFirstName(userDetails.getFirstname());
		storedUserDetails.setLastName(userDetails.getLastname());
		
		users.put(userId, storedUserDetails);
		
		return storedUserDetails;
	}
	
	@DeleteMapping(path="/id")
	public ResponseEntity<Void> deleteUser(@PathVariable String Id)
	{
		users.remove(Id);
		
		return ResponseEntity.noContent().build();
	}
	
}
