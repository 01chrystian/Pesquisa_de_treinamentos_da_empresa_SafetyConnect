package br.com.search.functions.safetyconnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.search.functions.safetyconnect.dto.TrainingDTO;
import br.com.search.functions.safetyconnect.model.Training;
import br.com.search.functions.safetyconnect.service.TrainingService;

@Controller
@RequestMapping(value = "/treinamento")
public class TrainingController {
	
	@Autowired
	private TrainingService service;
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<TrainingDTO>> findAll() throws Exception {
		return new ResponseEntity<List<TrainingDTO>>(service.findAll(), HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping(value= "/{id}")
	public ResponseEntity<List<TrainingDTO>> findById(@PathVariable Long id) throws Exception{
		return new ResponseEntity<List<TrainingDTO>>(service.findById(id), HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping(value= "/cadastrar")
	public ResponseEntity<String> findOneTrainingOfValidate(@RequestBody Training nametraining) throws Exception{
		String resTraining = service.findOneTrainingOfValidate(nametraining);
		if(resTraining != null) {
			//HttpStatus.CREATED == 201
		   return new ResponseEntity<String>(resTraining, HttpStatus.CREATED);
		}else {
			//HttpStatus.OK == 200
			return new ResponseEntity<String>("Cadastrado com sucesso!", HttpStatus.OK);		
		}
	
	}
	
	
}
