package pw.elka.pik.mkdev1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pw.elka.pik.mkdev1.domain.Project;
import pw.elka.pik.mkdev1.domain.User;
import pw.elka.pik.mkdev1.repository.ProjectRepository;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by mmudel on 23.04.2016.
 */
@Service
@Transactional
public class ProjectService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Inject
    private ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public Optional<Project> getProjectByName(String name) {
        return projectRepository.findOneByName(name);
    }
    
    @Transactional(readOnly = true)
    public Optional<Project> getProjectWithUsersByName(String name) {
        return projectRepository.findOneByName(name).map(p -> {
            p.getUsers().size();
            return p;
        });
    }
}
