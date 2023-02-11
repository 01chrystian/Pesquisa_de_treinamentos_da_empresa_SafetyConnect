package br.com.search.functions.safetyconnect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.search.functions.safetyconnect.model.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration,Long>{

	@Query(nativeQuery=true, value = "select * from tb_registration where tb_registration.email = :email")
	Optional<Registration> findOneRegistration(String email);
	
	@Query(nativeQuery=true, value = "select * from tb_registration where tb_registration.email = :email and tb_registration.password = :password")
	Optional<Registration> findOneRecordValidateTheUser(String email, String password);
}
