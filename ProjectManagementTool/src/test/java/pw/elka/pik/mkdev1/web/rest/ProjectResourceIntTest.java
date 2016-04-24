package pw.elka.pik.mkdev1.web.rest;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.print.Pageable;
import java.util.HashSet;
import java.util.Set;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.commons.lang.NotImplementedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pw.elka.pik.mkdev1.ProjectManagementToolApp;
import pw.elka.pik.mkdev1.domain.Authority;
import pw.elka.pik.mkdev1.domain.Project;
import pw.elka.pik.mkdev1.domain.User;
import pw.elka.pik.mkdev1.repository.ProjectRepository;
import pw.elka.pik.mkdev1.security.AuthoritiesConstants;
import pw.elka.pik.mkdev1.service.ProjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProjectManagementToolApp.class)
@WebAppConfiguration
@IntegrationTest
public class ProjectResourceIntTest {
	@Inject
	private ProjectRepository projectRepository;
	
	@Inject
	private ProjectRepository mockProjectRepository;
	
	@Inject
	private ProjectService projectService;
	
	@Inject
	private ProjectService mockProjectService;
	
	@Inject 
	private Pageable pageable;

    private MockMvc restProjectMockMvc;

    private MockMvc restMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        ProjectResource projectResource = new ProjectResource();
        ReflectionTestUtils.setField(projectResource, "projectRepository", projectRepository);
        ReflectionTestUtils.setField(projectResource, "projectService", projectService);

        ProjectResource projectMockResource = new ProjectResource();
        ReflectionTestUtils.setField(projectMockResource, "projectRepository", mockProjectRepository);
        ReflectionTestUtils.setField(projectMockResource, "projectService", mockProjectService);

        this.restMvc = MockMvcBuilders.standaloneSetup(projectResource).build();
        this.restProjectMockMvc = MockMvcBuilders.standaloneSetup(projectMockResource).build();
    }


    @Test
    public void testgetAllProjects() throws Exception { 
    	Project project = new Project();
    	project.setName("test");
    	project.setMembersCount(5L);
    	
    	throw new NotImplementedException();
    }


    @Test
    public void testGetExistingProject() throws Exception { 
    	Project project = new Project();
    	project.setName("test");
    	project.setMembersCount(5L);
    	
    	when(mockProjectService.getProjectByName("test")).thenReturn(Optional.of(project));
    	
    	restProjectMockMvc.perform(get("/api/project/test")
    			.accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
    			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
    			.andExpect(jsonPath("$.name").value("test"))
    			.andExpect(jsonPath("$.membersCount").value(5L));
    }
}
