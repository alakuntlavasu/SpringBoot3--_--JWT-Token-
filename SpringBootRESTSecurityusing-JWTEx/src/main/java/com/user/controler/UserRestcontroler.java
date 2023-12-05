package com.user.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.modal.User;
import com.user.modal.UserRequest;
import com.user.modal.UserResponse;
import com.user.service.IUserService;
import com.user.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserRestcontroler {
	
	@Autowired
   private IUserService service;
	
	@Autowired
	private JwtUtil util;
	
	//1. save user data in database
	
	@PostMapping("/save")
	public ResponseEntity<String> saveuser(@RequestBody User user){
		
		Integer id=service.saveUser(user);
		String body="User'"+id+"'saved";
		return  ResponseEntity.ok(body);
		
	}
	
	// 2. validate user and generate token (login)
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest request){
		
		String token =util.generateToken(request.getUsername());
		return ResponseEntity.ok(new UserResponse(token,"success!"));
		
	}
	
}
