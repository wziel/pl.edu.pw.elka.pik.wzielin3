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
import pw.elka.pik.mkdev1.repository.BoardRepository;
import pw.elka.pik.mkdev1.repository.TaskListRepository;
import pw.elka.pik.mkdev1.security.SecurityUtils;
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
    @Inject
    private BoardRepository boardRepository;
    
	public void create(TaskListDTO taskListDTO) {
		boardRepository.findOneById(taskListDTO.getBoardId()).ifPresent(b ->
		{
			final TaskList taskList = new TaskList();
			taskList.setName(taskListDTO.getName());
			b.getTaskLists().add(taskList);
			taskListRepository.save(taskList);
			boardRepository.save(b);
		});
	}
	
	public void update(TaskListDTO taskListDTO) {
		taskListRepository.findOneById(taskListDTO.getId()).ifPresent(l -> {
			l.setName(taskListDTO.getName());
			taskListRepository.save(l);
		});
	}

	public boolean exists(Long id) {
		return taskListRepository.findOneById(id).isPresent();
	}

    @Transactional(readOnly = true)
    public Optional<TaskListDTO> getById(Long id) {
        return taskListRepository.findOneById(id).map(TaskListDTO::new);
    }
}
