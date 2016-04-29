package pw.elka.pik.mkdev1.service;

/**
 * Created by mmudel on 29.04.2016.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pw.elka.pik.mkdev1.ProjectManagementToolApp;
import pw.elka.pik.mkdev1.domain.Project;
import pw.elka.pik.mkdev1.web.rest.dto.ProjectDTO;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProjectManagementToolApp.class)
@WebAppConfiguration
@IntegrationTest
@Rollback
@Transactional
public class ProjectServiceIntTest {
    @Autowired
    ProjectService projectService;

    @Test
    public void createProject_Normal_ProjectFound() throws Exception {
        ///Given
        ProjectDTO projectDTO = new ProjectDTO("forTest");
        ///When
        projectService.createProject(projectDTO);
        Optional<Project> project = projectService.getProjectByName("forTest");
        ///Then
        assertTrue(project.get().getMembersCount().equals(1L));
    }

    @Test(expected=DataIntegrityViolationException.class)
    public void createProject_ProjectExists_() throws Exception {
        ///Given
        ProjectDTO firstProject = new ProjectDTO("forTest", 2L);
        ProjectDTO secondProject = new ProjectDTO("forTest", 5L);
        projectService.createProject(firstProject);
        ///When
        projectService.createProject(secondProject);
        ///Then
        /// DataIntegrityViolationException expected
    }
}
