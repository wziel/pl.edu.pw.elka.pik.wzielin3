package pw.elka.pik.mkdev1.repository;

import pw.elka.pik.mkdev1.domain.TaskList;

import java.time.ZonedDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {
	//Ale po co to komu? ale dlaczego?
	//Jeśli trzeba serwis to do serwisu, a jeśli nie to niepotrzebne ;) 
    Optional<TaskList> findOneById(Long id);
}
