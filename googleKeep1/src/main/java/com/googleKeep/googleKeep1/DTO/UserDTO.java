package com.googleKeep.googleKeep1.DTO;

import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

	@Pattern(regexp = "^[A-Z]{1}[a-z]{3,}$", message = "Invalid data")
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String birthDate;
	private String password;

}
