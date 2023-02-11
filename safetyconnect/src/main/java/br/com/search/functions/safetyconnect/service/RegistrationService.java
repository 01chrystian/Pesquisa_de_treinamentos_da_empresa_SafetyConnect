package br.com.search.functions.safetyconnect.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.search.functions.safetyconnect.dto.RegistrationDTO;
import br.com.search.functions.safetyconnect.model.Registration;
import br.com.search.functions.safetyconnect.repository.RegistrationRepository;

@Service
public class RegistrationService {

	@Autowired
	private RegistrationRepository registrationRepository;

	public Registration findById(Long id) {
		Optional<Registration> userRegistration = registrationRepository.findById(id);
		if (!userRegistration.isEmpty()) {
			return userRegistration.get();
		} else {
			return null;
		}
	}

	public List<Registration> findAllRegistration() {
		List<Registration> users = registrationRepository.findAll();
		return users;
	}
	
	public String findOneRegistration(String email) {
		Optional<Registration> user = registrationRepository.findOneRegistration(email); 
		if(!user.isEmpty()) {
			return "Usuário já cadastrado!";
		}else {
			return null;
		}
	}
	
	public boolean validateUser(String email, String password) {
		Optional<Registration> userValidate = registrationRepository.findOneRecordValidateTheUser(email, password);
		if(!userValidate.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}

	public List<RegistrationDTO> findAllRegistrationDTO() {
		List<Registration> users = registrationRepository.findAll();
		List<RegistrationDTO> usersDto = new ArrayList<RegistrationDTO>();
		
		users.forEach(rgs -> {
			RegistrationDTO rgsDto = new RegistrationDTO();

			rgsDto.setId(rgs.getId());
			rgsDto.setName(rgs.getName());
			rgsDto.setEmail(rgs.getEmail());

			usersDto.add(rgsDto);
		});
		return usersDto;
	}

	public void saveUser(Registration setRgs) {
		registrationRepository.save(setRgs);
	}
}
