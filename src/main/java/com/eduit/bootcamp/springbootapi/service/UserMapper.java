package com.eduit.bootcamp.springbootapi.service;

import com.eduit.bootcamp.springbootapi.db.entity.UserEntity;
import com.eduit.bootcamp.springbootapi.model.UserDTO;

public interface UserMapper {

	/**
	 * This method create a UserEntity from given UserDTO.
	 * 
	 * @param theUser the User DTO to extract data. It cannot be null.
	 * @return The new user entity. Never null.
	 */
	UserEntity mapUser(final UserDTO theUser);
	
	/**
	 * This method create a UserEntity from given UserDTO.
	 * This also encrypt the password to the destination entity.
	 * 
	 * @param theUser the User DTO to extract data. It cannot be null.
	 * @return The new user entity. Never null.
	 */
	UserEntity mapUserEncoded(final UserDTO theUser);
	
	UserDTO mapUser(final UserEntity theUser);
}
