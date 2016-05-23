package pw.elka.pik.mkdev1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pw.elka.pik.mkdev1.domain.TaskList;
import pw.elka.pik.mkdev1.repository.TaskListRepository;
import pw.elka.pik.mkdev1.web.rest.dto.TaskListDTO;

import javax.inject.Inject;
import java.util.Optional;

@Service
@Transactional
public class TaskListService {

//    private final Logger log = LoggerFactory.getLogger(BoardService.class);
//
//    @Inject
//    private TaskListRepository taskListRepository;
//
//    @Transactional(readOnly = true)
//    public Optional<TaskListDTO> getById(Long id) {
//        return taskListRepository.findOneById(id).map(TaskListDTO::new);
//    }  
//    
//    @Transactional(readOnly = true)
//    public Optional<TaskListDTO> getDetailsById(Long id) {
//        return taskListRepository.findOneById(id).map(t -> {
//            t.getTasks().size();
//            return t;
//        }).map(TaskListDTO::new);
//    }
//    
//    @Transactional(readOnly = true)
//    public Page<TaskListDTO> getAll(Pageable pageable) {
//    	return taskListRepository.findAll(pageable).map(TaskListDTO::new);
//    }
// 
//    @Transactional(readOnly = true)
//    public boolean exists(Long id) {
//    	return getDetailsById(id).isPresent();
//    }
}
