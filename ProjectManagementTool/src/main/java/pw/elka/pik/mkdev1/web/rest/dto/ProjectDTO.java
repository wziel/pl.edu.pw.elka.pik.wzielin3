package pw.elka.pik.mkdev1.web.rest.dto;

import pw.elka.pik.mkdev1.domain.Project;
import pw.elka.pik.mkdev1.domain.User;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectDTO {

	@NotNull
    @Size(min = 1, max = 50)
    private String name;

    private Long membersCount;
    
    private Set<String> users;

    public ProjectDTO(){
        this.name = "";
        this.membersCount = 1L;
    }

    public ProjectDTO(String name){
        this.name = name;
        this.membersCount = 1L;
    }
	public ProjectDTO(Project project) {
		this(project.getName(), project.getMembersCount(), 
				project.getUsers().stream().map(User::getLogin).collect(Collectors.toSet()));
	}

	public ProjectDTO(String name, Long membersCount, Set<String> users){
		this.name = name;
        this.membersCount = membersCount;
        this.setUsers(users);
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

	public Set<String> getUsers() {
		return users;
	}

	public void setUsers(Set<String> users) {
		this.users = users;
	}

    @Override
	public String toString() {
		return "ProjectDTO{" +
            "name='" + this.name + '\'' +
            ", projectMembersCount=" + membersCount +
            "}";
	}
}
