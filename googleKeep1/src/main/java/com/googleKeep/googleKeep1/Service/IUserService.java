package com.googleKeep.googleKeep1.Service;

import java.util.List;

import com.googleKeep.googleKeep1.DTO.LoginDTO;
import com.googleKeep.googleKeep1.DTO.UserDTO;
import com.googleKeep.googleKeep1.Model.UserModel;

public interface IUserService {

	String registering(UserDTO userInfo);

	String loginMail(LoginDTO data, String token);

	String passwordChange(LoginDTO data, String newPassword);

	List<UserModel> allUsers(String token);

	UserModel getdetailById(String token);

	String deleteUserById(String token);

}
