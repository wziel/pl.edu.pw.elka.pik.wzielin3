package pw.elka.pik.mkdev1.web.rest.dto;

import pw.elka.pik.mkdev1.domain.Task;
import pw.elka.pik.mkdev1.domain.TaskList;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TaskDTO {

	
	public interface CreateChecks {
	}

	public interface UpdateChecks {
	}
	
	@NotNull(groups = {UpdateChecks.class})
	private Long id;
		
	@NotNull(groups = {UpdateChecks.class, CreateChecks.class})
    @Size(min = 1, max = 50, groups = {UpdateChecks.class, CreateChecks.class})
    private String name;
	
	private Long taskListId;
	
	public TaskDTO() {}
	
	public TaskDTO(Task task) {
		this(task.getName(), task.getId(), task.getTaskList().getId());
		}
		
	public TaskDTO(String name, Long id, Long taskListId){
		this.name = name;
		this.id = id;
		this.taskListId = taskListId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getTaskListId() {
		return taskListId;
	}

	public void setTaskListId(Long taskListId) {
		this.taskListId = taskListId;
	}

    @Override
	public String toString() {
		return "TaskDTO{" +
	            "name='" + this.name + '\''  +
	            "id='" + (this.id != null ? this.id.toString() : "") + '\''  +
            "}";
	}
}
