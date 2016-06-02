package pw.elka.pik.mkdev1.web.rest.dto;

import pw.elka.pik.mkdev1.domain.Board;
//import pw.elka.pik.mkdev1.domain.Task;
import pw.elka.pik.mkdev1.domain.TaskList;
import pw.elka.pik.mkdev1.web.rest.dto.TaskDTO.CreateChecks;
import pw.elka.pik.mkdev1.web.rest.dto.TaskDTO.UpdateChecks;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BoardDTO {

	public interface CreateChecks {
	}

	public interface UpdateChecks {
	}
	
	@NotNull(groups = {UpdateChecks.class})
	private Long id;
	
	@NotNull(groups = {UpdateChecks.class, CreateChecks.class})
    @Size(min = 1, max = 50,groups = {UpdateChecks.class, CreateChecks.class})
    private String name;
    
    private Set<TaskListDTO> taskLists;
    private Long projectId;
    
    public BoardDTO() { }

	public BoardDTO(Board board) {
		this(board.getName(), board.getId(), 
				board.getTaskLists().stream().map(TaskListDTO::new).collect(Collectors.toSet()), board.getProject().getId());}
		
	public BoardDTO(String name, Long id, Set<TaskListDTO> taskLists, Long projectId){
		this.name = name;
		this.setId(id);
        this.taskLists = taskLists;
        this.setProjectId(projectId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TaskListDTO> getTaskLists() {
		return taskLists;
	}

	public void setTaskLists(Set<TaskListDTO> taskLists) {
		this.taskLists = taskLists;
	}

    @Override
	public String toString() {
		return "BoardDTO{" +
	            "name='" + this.name + '\''  +
	            "id='" + (this.getId() != null ? this.getId().toString() : "null") + '\''  +
            "}";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
}
