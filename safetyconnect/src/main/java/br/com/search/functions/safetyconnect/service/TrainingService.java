package br.com.search.functions.safetyconnect.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.search.functions.safetyconnect.dto.TrainingDTO;
import br.com.search.functions.safetyconnect.model.Training;
import br.com.search.functions.safetyconnect.repository.TrainingRepository;

@Service
public class TrainingService {

	@Autowired
	private TrainingRepository repository;

	@Autowired
	private ProfessionService profService;

	public List<TrainingDTO> findAll() {
		List<Training> training = repository.findAll();
		List<TrainingDTO> trainingDto = new ArrayList<TrainingDTO>();

		training.stream().forEach(t -> {
			TrainingDTO tDto = new TrainingDTO();
			tDto.setId(t.getId());
			tDto.setName(t.getName());
			tDto.setProf(profService.findByTraining(t.getId()));
			trainingDto.add(tDto);
		});
		return trainingDto;
	}

	public List<TrainingDTO> findById(Long id) {
		List<Training> training = repository.findAll();
		List<TrainingDTO> trainingDto = new ArrayList<TrainingDTO>();

		training.stream().forEach(t -> {
			if (id == t.getId()) {
				TrainingDTO tDto = new TrainingDTO();
				tDto.setId(t.getId());
				tDto.setName(t.getName());
				tDto.setProf(profService.findByTraining(t.getId()));
				trainingDto.add(tDto);
			}
		});
		return trainingDto;
	}

	public String findOneTrainingOfValidate(Training training) {
		Optional<Training> validateTraining = repository.findOneTrainingOfValidate(training.getName());
		if (!validateTraining.isEmpty()) {
			return "Treinamento já cadastrado!";
		} else {
			repository.save(training);
			return null;
		}

	}
}
