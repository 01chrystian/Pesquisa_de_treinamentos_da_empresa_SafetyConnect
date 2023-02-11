package br.com.search.functions.safetyconnect.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.search.functions.safetyconnect.model.Profession;
import br.com.search.functions.safetyconnect.model.Registration;
import br.com.search.functions.safetyconnect.model.Training;
import br.com.search.functions.safetyconnect.repository.ProfessionRepository;
import br.com.search.functions.safetyconnect.repository.RegistrationRepository;
import br.com.search.functions.safetyconnect.repository.TrainingRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ProfessionRepository professionRepository;

	@Autowired
	private TrainingRepository trainingRepository;

	@Autowired
	private RegistrationRepository registrationRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Registration rgs1 = new Registration("Pedro Gonçalves1", "pgoncalves@gmail.com1", "1234");
		Registration rgs2 = new Registration("Pedro Gonçalves2", "pgoncalves@gmail.com2", "12345");
		Registration rgs3 = new Registration("Pedro Gonçalves3", "pgoncalves@gmail.com3", "123456");
		Registration rgs4 = new Registration("Pedro Gonçalves4", "pgoncalves@gmail.com4","1234567");
		
		registrationRepository.saveAll(Arrays.asList(rgs1, rgs2,rgs3, rgs4));

		Training train1 = new Training("Treinamento1");
		Training train2 = new Training("Treinamento2");

		trainingRepository.saveAll(Arrays.asList(train1, train2));

		Profession func1 = new Profession("7170-20", "Auxiliar de pedreiro", train1.getId());
		Profession func2 = new Profession("7152-10", "Pedreiro", train2.getId());
		Profession func3 = new Profession("7152-15", "Pedreiro (chaminés industriais)", train1.getId());
		Profession func4 = new Profession("2011-05", "Bioengenheiro", train2.getId());
		Profession func5 = new Profession("2221-10", "Engenheiro agrônomo", train1.getId());
		Profession func6 = new Profession("7241-15", "Bombeiro eletricista", train2.getId());
		Profession func7 = new Profession("7156-15", "Eletricista auxiliar", train1.getId());
		Profession func8 = new Profession("5211-40", "Atendente balconista", train2.getId());
		Profession func9 = new Profession("4223-15", "Atendente central telemarketing", train1.getId());
		Profession func10 = new Profession("4132-05", "Atendente de agência", train2.getId());

		professionRepository
				.saveAll(Arrays.asList(func1, func2, func3, func4, func5, func6, func7, func8, func9, func10));

	}

}
