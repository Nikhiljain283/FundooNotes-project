package com.googleKeep.googleKeep1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.googleKeep.googleKeep1.DTO.LoginDTO;
import com.googleKeep.googleKeep1.DTO.ResponseDTO;
import com.googleKeep.googleKeep1.DTO.UserDTO;
import com.googleKeep.googleKeep1.Model.UserModel;
import com.googleKeep.googleKeep1.Service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService userService;

	@GetMapping("/hello")
	public String response() {
		return "hii";
	}

	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> register(@RequestBody UserDTO userInfo) {
		String user = userService.registering(userInfo);
		ResponseDTO responseDto = new ResponseDTO("User is registered", user);
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

	}
	
//	token = eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo0fQ._yFOXoZJaAhjDadEvfHZnra9wocH3yehROs9cssMO9Y

	@GetMapping("/login")
	public ResponseEntity<ResponseDTO> loggingEmail(@RequestBody LoginDTO data, @RequestHeader String token) {
		String response = userService.loginMail(data, token);
		ResponseDTO responseDto = new ResponseDTO("login is successful for user", response);
		return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);

	}

	@PutMapping("/changePass/{newPassword}")
	public ResponseEntity<ResponseDTO> changePassword(@RequestBody LoginDTO data, @PathVariable String newPassword) {
		String response = userService.passwordChange(data, newPassword);
		ResponseDTO responseDto = new ResponseDTO("password change is successful for user", response);
		return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);

	}

	@GetMapping("/allUser")
	public ResponseEntity<ResponseDTO> getAllUsers(@RequestHeader String token) {
		List<UserModel> user = userService.allUsers(token);
		ResponseDTO responseDTO = new ResponseDTO("All User details found!", user);
		return new ResponseEntity(responseDTO, HttpStatus.OK);
	}

	@GetMapping("/get")
	public ResponseEntity<ResponseDTO> getById(@RequestHeader String token) {
		UserModel response = userService.getdetailById(token);
		ResponseDTO responseDto = new ResponseDTO("Id is found", response);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@DeleteMapping("/remove")
	ResponseEntity<ResponseDTO> deleteUserById(@RequestHeader String token) {
		String userModel = userService.deleteUserById(token);
		ResponseDTO responseDto = new ResponseDTO("User deleted :", userModel);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
}
