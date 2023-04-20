package com.googleKeep.googleKeep1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.googleKeep.googleKeep1.Model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
	
	@Query(value = "select * from database1.user_model where user_model.email = :email", nativeQuery = true)
	UserModel findDataByEmailId(String email);

}
