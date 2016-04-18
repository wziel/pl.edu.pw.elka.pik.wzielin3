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
	
	public ProjectDTO(Project project) {
		this(project.getName());
	}

	public ProjectDTO(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "ProjectDTO{name='" + this.name + "'}";
	}
}
