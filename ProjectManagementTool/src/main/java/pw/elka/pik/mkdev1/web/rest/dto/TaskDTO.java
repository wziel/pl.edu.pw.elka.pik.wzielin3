package pw.elka.pik.mkdev1.web.rest.dto;

import pw.elka.pik.mkdev1.domain.Task;
import pw.elka.pik.mkdev1.domain.TaskList;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TaskDTO {

	@NotNull
	private Long id;
		
	@NotNull
    @Size(min = 1, max = 50)
    private String name;
	
	public TaskDTO() {}
	
	public TaskDTO(Task task) {
		this(task.getName(), task.getId());
		}
		
	public TaskDTO(String name, Long id){
		this.name = name;
		this.id = id;
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

    @Override
	public String toString() {
		return "TaskDTO{" +
	            "name='" + this.name + '\''  +
	            "id='" + (this.id != null ? this.id.toString() : "") + '\''  +
            "}";
	}
}
