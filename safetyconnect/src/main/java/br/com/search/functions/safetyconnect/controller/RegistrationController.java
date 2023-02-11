package br.com.search.functions.safetyconnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.search.functions.safetyconnect.dto.RegistrationDTO;
import br.com.search.functions.safetyconnect.model.Registration;
import br.com.search.functions.safetyconnect.service.RegistrationService;

@RestController
@RequestMapping(value = "/usuario")
public class RegistrationController {

	@Autowired
	private RegistrationService service;

	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<Registration>> findAll() throws Exception {
		List<Registration> list = service.findAllRegistration();
		return new ResponseEntity<List<Registration>>(list, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "/{id}")
	public ResponseEntity<Registration> findById(@PathVariable Long id) throws Exception {
		Registration regisId = service.findById(id);
		return new ResponseEntity<Registration>(regisId, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "/buscar")
	public ResponseEntity<List<RegistrationDTO>> findAllRegistrationDTO() throws Exception {
		List<RegistrationDTO> listRegistration = service.findAllRegistrationDTO();
		return new ResponseEntity<List<RegistrationDTO>>(listRegistration, HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping(value = "/cadastrar/assinantes")
	public ResponseEntity<String> save(@RequestBody Registration registrationUser) throws Exception {
		String userExist = service.findOneRegistration(registrationUser.getEmail());
		if (userExist != null) {
			//HttpStatus.CREATED = 201
			return new ResponseEntity<String>(userExist,HttpStatus.CREATED);
		} else {
			service.saveUser(registrationUser);
			//HttpStatus.OK == 200
			return new ResponseEntity<String>("Você foi cadastrado com sucesso!", HttpStatus.OK);
		}
	}
	
	@CrossOrigin
	@PostMapping(value = "/logar/assinantes")
	public ResponseEntity<Boolean> validateUser(@RequestBody Registration registrationUser) throws Exception {
		Boolean userValidate = service.validateUser(registrationUser.getEmail(), registrationUser.getPassword());
		if (userValidate != false) {
			//HttpStatus.CREATED = 201
			return new ResponseEntity<Boolean>(userValidate,HttpStatus.CREATED);
		} else {
			//HttpStatus.OK == 200
			return new ResponseEntity<Boolean>(userValidate, HttpStatus.OK);
		}
	}
}
