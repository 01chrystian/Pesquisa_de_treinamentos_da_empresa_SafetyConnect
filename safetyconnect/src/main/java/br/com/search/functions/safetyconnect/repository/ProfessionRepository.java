package br.com.search.functions.safetyconnect.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.search.functions.safetyconnect.model.Profession;
import jakarta.transaction.Transactional;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession,Long>{

	@Query(nativeQuery=true, value = "select id, cbo, description, id_training from tb_profession where id_training = :id_training")
	List<Profession> findByIdTraining(Long id_training);
	
	@Query(nativeQuery=true, value = "select id, cbo, description, id_training from tb_profession")
	List<Profession> findAllProfessions();
	
	@Query(nativeQuery=true, value = "select tb_profession.id, tb_profession.cbo, tb_profession.description, tb_profession.id_training "
			+ "from tb_profession, tb_training "
			+ "where tb_training.id = tb_profession.id_training "
			+ "and LOWER(tb_profession.description) = :description or tb_profession.cbo = :cbo")
	Profession findProfession(String description, String cbo);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery=true, value = "update tb_profession set tb_profession.description = :description "
			+ "where tb_profession.cbo = :cbo")
	int updateRecordByName(String description, String cbo);
	
	@Query(nativeQuery=true, value = "select tb_profession.id, tb_profession.cbo, tb_profession.description, tb_profession.id_training "
			+ "from tb_profession, tb_training "
			+ "where tb_training.id = tb_profession.id_training "
			+ "and tb_profession.cbo = :cbo")
	Profession findProfessionCBO(String cbo);
	
	@Query(nativeQuery=true, value ="select tb_profession.id, tb_profession.cbo, tb_profession.description, tb_profession.id_training "
			+ "from tb_profession "
			+ "where tb_profession.cbo = :cbo")
	Optional<Profession> findRegisterProfession(String cbo);
}
