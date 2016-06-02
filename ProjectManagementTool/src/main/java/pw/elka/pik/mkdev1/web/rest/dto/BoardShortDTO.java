package pw.elka.pik.mkdev1.web.rest.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import pw.elka.pik.mkdev1.domain.Board;
import pw.elka.pik.mkdev1.web.rest.dto.TaskDTO.CreateChecks;
import pw.elka.pik.mkdev1.web.rest.dto.TaskDTO.UpdateChecks;

public class BoardShortDTO {
	
	public interface CreateChecks {
	}

	public interface UpdateChecks {
	}

	@NotNull(groups = {UpdateChecks.class, CreateChecks.class})
    @Size(min = 1, max = 50,groups = {UpdateChecks.class, CreateChecks.class})
    private String name;
	
	@NotNull(groups = {UpdateChecks.class})
    private Long id;
    
	public BoardShortDTO(Board board) {
		this(board.getName(), board.getId());
	}

    public BoardShortDTO(String name, Long id){
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
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
