package br.com.search.functions.safetyconnect.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.search.functions.safetyconnect.dto.ProfessionDTO;
import br.com.search.functions.safetyconnect.model.Profession;
import br.com.search.functions.safetyconnect.model.Training;
import br.com.search.functions.safetyconnect.repository.ProfessionRepository;
import br.com.search.functions.safetyconnect.repository.TrainingRepository;

@Service
public class ProfessionService {

	@Autowired
	private ProfessionRepository repository;

	@Autowired
	private TrainingRepository trainingRepository;

	public List<Profession> findAllProfessions() {
		List<Profession> prof = repository.findAllProfessions();
		return prof;
	}
	//pode retornar jakarta.persistence.NonUniqueResultException: query did not return a unique result: 2
	//pois poderá o adm ter cadastrado duas ou mais profissoes com  o mesmo nome
	public ProfessionDTO findProfessionDTO(String description, String cbo) {
		Profession oneProf = repository.findProfession(description, cbo);
		ProfessionDTO oneProfDto = new ProfessionDTO();

		oneProfDto.setId(oneProf.getId());
		oneProfDto.setCbo(oneProf.getCbo());
		oneProfDto.setDescription(oneProf.getDescription());

		Optional<Training> oneTraining = trainingRepository.findById(oneProf.getId_training());
		oneProfDto.setName_training(oneTraining.get().getName());

		return oneProfDto;
	}

	public Profession findById(Long id) {
		Optional<Profession> profId = repository.findById(id);
		if (!profId.isEmpty()) {
			return profId.get();
		} else {
			return null;
		}
	}

	public List<ProfessionDTO> findByTraining(Long id) {
		List<Profession> profession = repository.findByIdTraining(id);
		List<ProfessionDTO> professionDTO = new ArrayList<ProfessionDTO>();
		profession.stream().forEach(p -> {
			ProfessionDTO prof = new ProfessionDTO();
			prof.setId(p.getId());
			prof.setCbo(p.getCbo());
			prof.setDescription(p.getDescription());
			professionDTO.add(prof);
		});
		return professionDTO;
	}

	public String findRegisterProfession(Profession profRegister) {
       Optional<Profession> registerProf = repository.findRegisterProfession(profRegister.getCbo());
       if(!registerProf.isEmpty()) {
    	   return "Está profissão já está cadastrada!";
       }else {
    	   repository.save(profRegister);
    	   return null;
       }
	}
	
	public String updateProfessionOrCbo(Profession profOrCbo) {
		Optional<Profession> returnStatusUpdateProf = repository.professionOfUpdate(profOrCbo.getDescription(), profOrCbo.getCbo());
		System.out.println(returnStatusUpdateProf);	
		return "Atualizado com sucesso!";
	}
	
}
