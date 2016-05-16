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
@Table(name = "project")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private Long membersCount;
    
    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "jhi_user_project",
        joinColumns = {@JoinColumn(name = "project_id")},
        inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<User> users = new HashSet<>();
    
    @JsonIgnore
    @ManyToMany
    @JoinTable(
    		name="board_project",
    		joinColumns = {@JoinColumn(name = "project_id")},
    		inverseJoinColumns = {@JoinColumn(name = "board_id")})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Board> boards = new HashSet<>();

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

    public Long getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(Long membersCount) {
        this.membersCount = membersCount;
    }
    
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

	public Set<Board> getBoards() {
		return boards;
	}

	public void setBoards(Set<Board> boards) {
		this.boards = boards;
	}
}
