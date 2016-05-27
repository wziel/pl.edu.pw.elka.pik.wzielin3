package pw.elka.pik.mkdev1.service;

import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pw.elka.pik.mkdev1.domain.Board;
import pw.elka.pik.mkdev1.domain.Project;
import pw.elka.pik.mkdev1.domain.TaskList;
import pw.elka.pik.mkdev1.repository.TaskListRepository;
import pw.elka.pik.mkdev1.web.rest.dto.ProjectDTO;
import pw.elka.pik.mkdev1.web.rest.dto.TaskListDTO;

import javax.inject.Inject;
import java.util.Optional;

@Service
@Transactional
public class TaskListService {
	
    private final Logger log = LoggerFactory.getLogger(BoardService.class);
    @Inject
    private TaskListRepository taskListRepository;
    
	public void create(TaskListDTO taskListDTO) {
		throw new NotImplementedException();
	}
	
	public void update(TaskListDTO taskListDTO) {
		throw new NotImplementedException();
	}

	public boolean exists(Long id) {
		return taskListRepository.findOneById(id).isPresent();
	}

    @Transactional(readOnly = true)
    public Optional<TaskListDTO> getById(Long id) {
        return taskListRepository.findOneById(id).map(TaskListDTO::new);
    }
}
