package pw.elka.pik.mkdev1.web.rest.dto;

import pw.elka.pik.mkdev1.domain.Board;
import pw.elka.pik.mkdev1.domain.Project;
import pw.elka.pik.mkdev1.domain.User;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectDTO {

    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    private Long membersCount;

    private Set<String> users;

    private Set<BoardShortDTO> boards;

    private String description;

    public ProjectDTO() {
        this("", 1L, Collections.emptySet(), Collections.emptySet(), "");
    }

    public ProjectDTO(String name) {
        this(name, 1L, Collections.emptySet(), Collections.emptySet(), "");
    }

    public ProjectDTO(Project project) {
        this(project.getName(), project.getMembersCount(),
            project.getUsers().stream().map(User::getLogin).collect(Collectors.toSet()),
            project.getBoards().stream().map(BoardShortDTO::new).collect(Collectors.toSet()),
            project.getDescription());
    }

    public ProjectDTO(String name, Long membersCount, Set<String> users, Set<BoardShortDTO> boards, String description) {
        this.name = name;
        this.membersCount = membersCount;
        this.setUsers(users);
        this.setBoards(boards);
        this.description = description;
    }

    public ProjectDTO(String name, Long membersCount) {
        this(name, membersCount, Collections.emptySet(), Collections.emptySet(), "");
    }

    public ProjectDTO(String name, Long membersCount, String description){
        this(name, membersCount, Collections.emptySet(), Collections.emptySet(), description);
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

    public Set<BoardShortDTO> getBoards() {
        return boards;
    }

    public void setBoards(Set<BoardShortDTO> boards) {
        this.boards = boards;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
            "name='" + this.name + '\'' +
            ", projectMembersCount=" + membersCount +
            "}";
    }
}
