package pw.elka.pik.mkdev1.web.rest.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import pw.elka.pik.mkdev1.domain.Board;

public class BoardShortDTO {

	@NotNull
    @Size(min = 1, max = 50)
    private String name;
	
	@NotNull
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
