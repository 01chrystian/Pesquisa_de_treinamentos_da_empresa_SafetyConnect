package br.com.search.functions.safetyconnect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.search.functions.safetyconnect.model.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training,Long>{

	@Query(nativeQuery=true, value = "select * from tb_training where LOWER(tb_training.name) = :training")
	Optional<Training> findOneTrainingOfValidate(String training);
	
}
