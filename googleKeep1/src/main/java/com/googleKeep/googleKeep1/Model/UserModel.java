package com.googleKeep.googleKeep1.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.googleKeep.googleKeep1.DTO.LoginDTO;
import com.googleKeep.googleKeep1.DTO.UserDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class UserModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String firstName;
	private String lastName;
	private String address;
	private String birthDate;
	private String password;
	private String email;

	public UserModel(UserDTO userDto) {

		this.firstName = userDto.getFirstName();
		this.lastName = userDto.getLastName();
		this.email = userDto.getEmail();
		this.address = userDto.getAddress();
		this.birthDate = userDto.getBirthDate();
		this.password = userDto.getPassword();

	}

	public UserModel(LoginDTO loginDto) {
		this.email = loginDto.getEmail();
		this.password = loginDto.getPassword();
	}

}
