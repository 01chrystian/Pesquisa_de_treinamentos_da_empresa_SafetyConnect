package br.com.search.functions.safetyconnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.search.functions.safetyconnect.dto.ProfessionDTO;
import br.com.search.functions.safetyconnect.model.Profession;
import br.com.search.functions.safetyconnect.service.ProfessionService;

@RestController
@RequestMapping(value = "/profissao")
public class ProfessionController {

	@Autowired
	private ProfessionService service;

	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<Profession>> findAll() throws Exception {
		List<Profession> list = service.findAllProfessions();
		return new ResponseEntity<List<Profession>>(list, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "/busca")
	public ResponseEntity<ProfessionDTO> findProfessionDTO(@RequestParam String description, @RequestParam String cbo)
			throws Exception {
		ProfessionDTO returnOneProf = service.findProfessionDTO(description, cbo);
		return new ResponseEntity<ProfessionDTO>(returnOneProf, HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping(value = "/cadastrar/profissao")
	public ResponseEntity<String> findRegisterProfession(@RequestBody Profession registrationProfession) throws Exception {
		String returnOneProfRegistration = service.findRegisterProfession(registrationProfession);
		if (returnOneProfRegistration != null) {
			// HttpStatus.CREATED = 201
			return new ResponseEntity<String>(returnOneProfRegistration, HttpStatus.CREATED);
		} else {
			// HttpStatus.OK == 200
			return new ResponseEntity<String>("Cadastrado com sucesso!", HttpStatus.OK);
		}
	}

	@CrossOrigin
	@GetMapping(value = "/{id}")
	public ResponseEntity<Profession> findById(@PathVariable Long id) throws Exception {
		Profession obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@CrossOrigin
	@PatchMapping(value = "/atualizar")
	public ResponseEntity<String> updateProfessionOrCbo(Profession professionOrCbo) {
		String returnStatusUpdate = service.updateProfessionOrCbo(professionOrCbo);
		return new ResponseEntity<String>(returnStatusUpdate, HttpStatus.OK);
	}
}
