package pw.elka.pik.mkdev1.web.rest.dto;

import pw.elka.pik.mkdev1.domain.Project;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectDTO {

	@NotNull
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    private Long membersCount;

	public ProjectDTO(Project project) {
		this(project.getName(), project.getMembersCount());
	}

	public ProjectDTO(String name, Long membersCount){
		this.name = name;
        this.membersCount = membersCount;
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

    @Override
	public String toString() {
		return "ProjectDTO{" +
            "name='" + this.name + '\'' +
            ", projectMembersCount=" + membersCount +
            "}";
	}
}
