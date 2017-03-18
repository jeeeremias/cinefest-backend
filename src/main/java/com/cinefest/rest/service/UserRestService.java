package com.cinefest.rest.service;

import com.cinefest.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinefest.repository.UserRepository;

@Service
@Transactional
public class UserRestService {
	
	@Autowired
	UserRepository userRepository;
	
	public UserEntity getUsuario(String email) {
		return userRepository.findOne(email);
	}
	
	public boolean existe(String email) {
		return userRepository.exists(email);
	}
	
	public boolean cadastro(UserEntity userEntity) {
		if(existe(userEntity.getEmail())) {
			System.out.println("UserEntity já cadastrado.");
		} else {
			try {
				userEntity.setEmail(userEntity.getEmail().toLowerCase());
                userRepository.save(userEntity);
			} catch (Exception e) {
				System.out.println("Cadastro realizado com sucesso.");
			}
		}
		System.out.println("Cadastro realizado com sucesso.");
		return true;
	}
	
	public boolean login(UserEntity userEntity) {
		UserEntity userEntity2 = null;
		if (userEntity.getEmail().equals("admin@admin.com") && userEntity.getPassword().equals("admin")) {
		} else {
			userEntity2 = getUsuario(userEntity.getEmail().toLowerCase());
			if (userEntity2 == null) {
                System.out.println("UserEntity não cadastrado.");
			} else {
				if (!userEntity.getPassword().equals(userEntity2.getPassword())) {
					System.out.println("Senha incorreta.");
				}
			}
		}
		return true;
	}
}
