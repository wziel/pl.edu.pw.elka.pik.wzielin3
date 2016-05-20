package pw.elka.pik.mkdev1.web.rest.dto;

import pw.elka.pik.mkdev1.domain.Board;
//import pw.elka.pik.mkdev1.domain.Task;
import pw.elka.pik.mkdev1.domain.TaskList;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BoardDTO {

	@NotNull
    @Size(min = 1, max = 50)
    private String name;
    
    private Set<String> taskLists;

    public BoardDTO(){
        this.name = "";
        this.taskLists= new HashSet<>();
    }

    public BoardDTO(String name){
        this.name = name;
        this.taskLists= new HashSet();
    }
	public BoardDTO(Board board) {
		this(board.getName(), board.getTaskLists().stream().map(TaskList::getName).collect(Collectors.toSet()));}

		
	public BoardDTO(String name, Set<String> taskLists){
		this.name = name;
        this.setTaskLists(taskLists);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getTaskLists() {
		return taskLists;
	}

	public void setTaskLists(Set<String> taskLists) {
		this.taskLists = taskLists;
	}

    @Override
	public String toString() {
		return "BoardDTO{" +
            "name='" + this.name + '\''  +
            "}";
	}
}
