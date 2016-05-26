package pw.elka.pik.mkdev1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pw.elka.pik.mkdev1.domain.Project;
import pw.elka.pik.mkdev1.repository.ProjectRepository;
import pw.elka.pik.mkdev1.web.rest.dto.ProjectDTO;

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

    @Transactional(readOnly = true)
    public Optional<ProjectDTO> getByName(String name) {
        return projectRepository.findOneByName(name).map(ProjectDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<ProjectDTO> getAll(Pageable pageable) {
    	return projectRepository.findAll(pageable).map(ProjectDTO::new);
    }

    public void createProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setMembersCount(1L);
        project.setDescription(projectDTO.getDescription());
        projectRepository.save(project);
        log.debug("Created Information for project: {}", project);
    }

    @Transactional(readOnly = true)
    public Optional<ProjectDTO> getDetailsByName(String name) {
        return projectRepository.findOneByName(name).map(p -> {
            p.getUsers().size();
            p.getBoards().size();
            return p;
        }).map(ProjectDTO::new);
    }

    @Transactional(readOnly = true)
    public boolean exists(String name) {
    	return getDetailsByName(name).isPresent();
    }
}
