package pw.elka.pik.mkdev1.web.rest.dto;

import pw.elka.pik.mkdev1.domain.TaskList;
//import pw.elka.pik.mkdev1.domain.Task;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TaskListDTO {

	@NotNull
    @Size(min = 1, max = 50)
    private String name;
    
    private Set<String> tasks;

    public TaskListDTO(){
        this.name = "";
        this.tasks= new HashSet<>();
    }

    public TaskListDTO(String name){
        this.name = name;
//        this.tasks= new HashSet();
    }
	public TaskListDTO(TaskList taskList) {
		this(taskList.getName());}

	// board.getTasks().stream().map(Task::getName).collect(Collectors.toSet()));
		
//	public BoardDTO(String name, Set<String> tasks){
//		this.name = name;
//        this.setTasks(tasks);
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Set<String> getTasks() {
//		return tasks;
//	}
//
//	public void setTasks(Set<String> tasks) {
//		this.tasks = tasks;
//	}

    @Override
	public String toString() {
		return "TaskListDTO{" +
            "name='" + this.name + '\''  +
            "}";
	}
}
