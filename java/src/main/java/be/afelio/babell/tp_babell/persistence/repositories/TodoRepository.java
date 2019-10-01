package be.afelio.babell.tp_babell.persistence.repositories;

import be.afelio.babell.tp_babell.persistence.entities.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {


    TodoEntity findOneByNameIgnoreCase(String name);

    TodoEntity findOneById(int idTodo);

    List<TodoEntity> findAllByProjectName(String projectName);
}
