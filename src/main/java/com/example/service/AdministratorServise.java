package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

/**
 * @author junya.yoshioka
 *
 */
@Service
public class AdministratorServise {

	@Autowired
	public AdministratorRepository administratorRepository;
	
	//パスワードのハッシュ化
//	@Autowired
//	private Password
	
	
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
	}
	
	public Administrator findByMailAddress(String mailAddress) {
		return administratorRepository.findByMailAddress(mailAddress);
	}
	
	public Administrator login(String mailAddress, String passward) {
		Administrator administrator = administratorRepository.findByMailAddressAndPassward(mailAddress, passward);
		return administrator;
	}
	
}
