package pw.elka.pik.mkdev1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pw.elka.pik.mkdev1.domain.Project;
import pw.elka.pik.mkdev1.domain.User;
import pw.elka.pik.mkdev1.repository.ProjectRepository;
import pw.elka.pik.mkdev1.repository.UserRepository;
import pw.elka.pik.mkdev1.security.SecurityUtils;
import pw.elka.pik.mkdev1.web.rest.dto.ProjectDTO;
import pw.elka.pik.mkdev1.web.rest.util.HeaderUtil;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by mmudel on 23.04.2016.
 */
@Service
@Transactional
public class ProjectService {

    private final Logger log = LoggerFactory.getLogger(ProjectService.class);

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<ProjectDTO> getById(Long id) {
        return projectRepository.findOneById(id).map(ProjectDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<ProjectDTO> getAllProjectsForCurrentUser(Pageable pageable) {
        User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).get();
        return projectRepository.findAllByUsers(user, pageable).map(ProjectDTO::new);
    }

    public void createProject(ProjectDTO projectDTO) {
        User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).get();
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.addUser(user);
        projectRepository.save(project);
        log.debug("Created Information for project: {}", project);
    }

    public ResponseEntity<ProjectDTO> modifyProject(ProjectDTO projectDTO) {
        return projectRepository.findOneById(projectDTO.getId())
            .map(project -> {
                project.setDescription(projectDTO.getDescription());
                return ResponseEntity.ok()
                    .headers(HeaderUtil.createAlert("A project is updated with identifier " + projectDTO.getName(), projectDTO.getName()))
                    .body(new ProjectDTO(project));
            })
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Transactional(readOnly = true)
    public Optional<ProjectDTO> getDetailsById(Long id) {
        return projectRepository.findOneById(id).map(p -> {
            p.getUsers().size();
            p.getBoards().size();
            return p;
        }).map(ProjectDTO::new);
    }

    public void deleteUserFromProject(Long id, String login) {
        Project project = projectRepository.findOneById(id).get();
        User user = userRepository.findOneByLogin(login).get();
        project.deleteUser(user);
        projectRepository.save(project);
        return;
    }

    public void addUserToProject(Long id, String login) {
        Project project = projectRepository.findOneById(id).get();
        User user = userRepository.findOneByLogin(login).get();
        project.addUser(user);
        projectRepository.save(project);
        return;
    }

    @Transactional(readOnly = true)
    public boolean exists(Long id) {
    	return getDetailsById(id).isPresent();
    }
}
