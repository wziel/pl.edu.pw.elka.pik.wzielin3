package pw.elka.pik.mkdev1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.time.ZonedDateTime;

@Entity
@Table(name = "task_list")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TaskList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = false, nullable = false)
    private String name;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "task_list_task",
        joinColumns = {@JoinColumn(name = "task_list_id", nullable = false)},
        inverseJoinColumns = {@JoinColumn(name = "task_id", nullable = false)})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Task> tasks = new HashSet<>();
    
    @JsonIgnore
    @OneToOne
    @JoinTable(
        name = "board_task_list",
        joinColumns = {@JoinColumn(name = "task_list_id", nullable = false)},
        inverseJoinColumns = {@JoinColumn(name = "board_id", nullable = false)})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Board board;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
    
	public Board  getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}
