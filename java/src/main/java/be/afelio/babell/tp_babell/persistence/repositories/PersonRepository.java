package be.afelio.babell.tp_babell.persistence.repositories;

import be.afelio.babell.tp_babell.persistence.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
    PersonEntity findOneByEmail(String email);
}
