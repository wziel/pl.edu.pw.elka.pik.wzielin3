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
    @Size(min = 1, max = 300)
    private String description;
	
	@NotNull
    @Size(min = 1, max = 50)
    private String name;
	
	public TaskDTO(Task task) {
		this(task.getName(), task.getId(), 
				task.getDescription());
		}
		
	public TaskDTO(String name, Long id, String description){
		this.name = name;
		this.id = id;
        this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    @Override
	public String toString() {
		return "TaskDTO{" +
	            "name='" + this.name + '\''  +
	            "id='" + this.id.toString() + '\''  +
	            "name='" + this.name + '\''
	            +
            "}";
	}
}
