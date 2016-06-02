package pw.elka.pik.mkdev1.web.rest.dto;

import pw.elka.pik.mkdev1.domain.TaskList;
import pw.elka.pik.mkdev1.web.rest.dto.TaskDTO.CreateChecks;
import pw.elka.pik.mkdev1.web.rest.dto.TaskDTO.UpdateChecks;
import pw.elka.pik.mkdev1.domain.Task;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TaskListDTO {

	public interface CreateChecks {
	}

	public interface UpdateChecks {
	}
	
	@NotNull(groups = {UpdateChecks.class})
	private Long id;
	
	@NotNull(groups = {UpdateChecks.class, CreateChecks.class})
    @Size(min = 1, max = 50,groups = {UpdateChecks.class, CreateChecks.class})
    private String name;
	
    private Set<TaskDTO> tasks;
    private Long boardId;
    
    public TaskListDTO() { }

    public TaskListDTO(final TaskList taskList)
    {
    	this(taskList.getName(), taskList.getId(), taskList.getTasks().stream().map(TaskDTO::new).collect(Collectors.toSet()), taskList.getBoard().getId());
    }
    
    public TaskListDTO(final String name, final Long id, final Set<TaskDTO> tasks, Long boardId)
    {
    	this.name = name;
    	this.id = id;
    	this.tasks = tasks;
    	this.setBoardId(boardId);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TaskDTO> getTasks() {
		return tasks;
	}

	public void setTasks(Set<TaskDTO> tasks) {
		this.tasks = tasks;
	}

    @Override
	public String toString() {
		return "TaskListDTO{" +
            "name='" + this.name + '\''  +
            "}";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
}
