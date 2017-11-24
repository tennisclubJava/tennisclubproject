package com.frenchies.tennisclub.facade;
import java.util.List;

import com.frenchies.tennisclub.service.facade.UserAuthenticateDTO;

//import cz.fi.muni.pa165.dto.UserAuthenticateDTO;
//import cz.fi.muni.pa165.dto.UserDTO;

public interface UserFacade {
	
	void deleteUser(Long id);
	void updateUser(UserDTO user);
	void createUser(UserDTO user, String unHashPassword);
//	void changePassword(UserDTO user, String newUnHashPassword);
	
	List<UserDTO> getAllUser();
		
	UserDTO findUserByName(String Name);
	
	UserDTO findUserById(Long userId);
	
	boolean authenticate(UserAuthenticateDTO u);

	boolean isAdmin(UserDTO u);
	
}