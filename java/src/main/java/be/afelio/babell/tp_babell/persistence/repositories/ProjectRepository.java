package be.afelio.babell.tp_babell.persistence.repositories;

import be.afelio.babell.tp_babell.persistence.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
}
