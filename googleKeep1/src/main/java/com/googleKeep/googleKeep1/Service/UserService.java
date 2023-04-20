package com.googleKeep.googleKeep1.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googleKeep.googleKeep1.DTO.LoginDTO;
import com.googleKeep.googleKeep1.DTO.UserDTO;
import com.googleKeep.googleKeep1.Exception.CustomException;
import com.googleKeep.googleKeep1.Model.UserModel;
import com.googleKeep.googleKeep1.Repository.UserRepository;
import com.googleKeep.googleKeep1.Util.EmailSenderService;
import com.googleKeep.googleKeep1.Util.Token;

@Service
public class UserService implements IUserService {

	@Autowired
	Token tokenUtil;

	@Autowired
	UserRepository repo;

	public String registering(UserDTO userInfo) {
		UserModel model = new UserModel(userInfo);
		repo.save(model);
		String token = tokenUtil.createToken(model.getUserId());
		return token;
	}

	public String loginMail(LoginDTO data, String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserModel> model = repo.findById(id);
		if (model.get().getEmail().equals(data.getEmail()) && model.get().getPassword().equals(data.getPassword())) {
			return "user login successful";
		} else {
			throw new CustomException("longin error");
		}
	}

	public String passwordChange(LoginDTO data, String newPassword) {

		UserModel model = repo.findDataByEmailId(data.getEmail());

		if (model.getEmail().equals(data.getEmail()) && model.getPassword().equals(data.getPassword())) {
			model.setPassword(newPassword);
			repo.save(model);
			return "password changed successfully";

		} else {
			throw new CustomException("Password is not changed");
		}

	}

	public List<UserModel> allUsers(String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserModel> model = repo.findById(id);
		if (model.isPresent()) {
			return repo.findAll();

		} else {

			throw new CustomException("Id is not present in table");
		}
	}

	public UserModel getdetailById(String token) {
		int id = tokenUtil.decodeToken(token);
		if (repo.findById(id).isPresent()) {
			return repo.findById(id).get();
		} else {
			throw new CustomException("Contact with " + id + " id is not found.");
		}

	}

	public boolean validateUser(String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserModel> model = repo.findById(id);
		if (model.isPresent()) {
			return true;
		} else {
			throw new CustomException("user is not validated");
		}
	}

	@Override
	public String deleteUserById(String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserModel> model = repo.findById(id);
		if(model.isPresent()) {
			repo.deleteById(id);
			return "User is deleted";
		}else {
			throw new CustomException("user not deleted");
		}
	
	}

}
