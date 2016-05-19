package pw.elka.pik.mkdev1.web.rest;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pw.elka.pik.mkdev1.ProjectManagementToolApp;
import pw.elka.pik.mkdev1.domain.Board;
import pw.elka.pik.mkdev1.domain.Project;
import pw.elka.pik.mkdev1.domain.User;
import pw.elka.pik.mkdev1.repository.ProjectRepository;
import pw.elka.pik.mkdev1.service.ProjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProjectManagementToolApp.class)
@WebAppConfiguration
@IntegrationTest
public class ProjectResourceIntTest {

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private ProjectService projectService;

    private MockMvc restMvc;

    private Optional<Project> testOptionalProject;

    private Project testProject;

    @Before
    public void setUp() {
        Set<User> users = new HashSet<>();
        Set<Board> boards = new HashSet<>();
        User user = new User();
        user.setId(1L);
        user.setFirstName("testUser");
        user.setLogin("testLogin");
        users.add(user);
        User user1 = new User();
        user1.setId(2L);
        user1.setFirstName("testUser2");
        user1.setLogin("testLogin2");
        users.add(user1);
        Board board = new Board();
        board.setId(1L);
        board.setName("board1");
        boards.add(board);
        Board board2 = new Board();
        board2.setId(2L);
        board2.setName("board2");
        boards.add(board2);
        Board board3 = new Board();
        board3.setId(3L);
        board3.setName("board3");
        boards.add(board3);
        
        testProject = new Project();
        testProject.setId(1L);
        testProject.setName("test");
        testProject.setMembersCount(2L);
        testProject.setUsers(users);
        testProject.setBoards(boards);
        testOptionalProject = Optional.of(testProject);
    }

    @Test
    public void getProject_ProjectExists_ReceiveExpectedValues() throws Exception {
        /// Given
        projectService = Mockito.mock(ProjectService.class);
        when(projectService.getProjectDetailsByName("test")).thenReturn(testOptionalProject);

        /* Injection members of testing class */
        ProjectResource projectResource = new ProjectResource();
        ReflectionTestUtils.setField(projectResource, "projectRepository", projectRepository);
        ReflectionTestUtils.setField(projectResource, "projectService", projectService);
        this.restMvc = MockMvcBuilders.standaloneSetup(projectResource).build();

        /// When
        restMvc.perform(get("/api/projects/test")
            .accept(MediaType.APPLICATION_JSON))
            /// Then
            .andExpect(status().isOk())
            .andExpect(jsonPath("membersCount").value(2))
            .andExpect(jsonPath("name").value("test"));
    }
    
    @Test
    public void getProject_ProjectExists_ReceiveUsers() throws Exception {
        /// Given
        projectService = Mockito.mock(ProjectService.class);
        when(projectService.getProjectDetailsByName("test")).thenReturn(testOptionalProject);

        /* Injection members of testing class */
        ProjectResource projectResource = new ProjectResource();
        ReflectionTestUtils.setField(projectResource, "projectRepository", projectRepository);
        ReflectionTestUtils.setField(projectResource, "projectService", projectService);
        this.restMvc = MockMvcBuilders.standaloneSetup(projectResource).build();

        /// When
        restMvc.perform(get("/api/projects/test")
            .accept(MediaType.APPLICATION_JSON))
            /// Then
            .andExpect(status().isOk())
            .andExpect(jsonPath("users[1]").value("testLogin"))
            .andExpect(jsonPath("users[0]").value("testLogin2"));
    }
    
    @Test
    public void getProject_ProjectExists_ReceiveBoards() throws Exception {
        /// Given
        projectService = Mockito.mock(ProjectService.class);
        when(projectService.getProjectDetailsByName("test")).thenReturn(testOptionalProject);

        /* Injection members of testing class */
        ProjectResource projectResource = new ProjectResource();
        ReflectionTestUtils.setField(projectResource, "projectRepository", projectRepository);
        ReflectionTestUtils.setField(projectResource, "projectService", projectService);
        this.restMvc = MockMvcBuilders.standaloneSetup(projectResource).build();

        /// When
        restMvc.perform(get("/api/projects/test")
            .accept(MediaType.APPLICATION_JSON))
            /// Then
            .andExpect(status().isOk())
            .andExpect(jsonPath("boards[0]").value("board2"))
            .andExpect(jsonPath("boards[1]").value("board3"))
            .andExpect(jsonPath("boards[2]").value("board1"));
    }

    @Test
    public void getProject_ProjectNotExist_ErrorStatus() throws Exception {
        ///Given
        /* Injection members of testing class */
        ProjectResource projectResource = new ProjectResource();
        ReflectionTestUtils.setField(projectResource, "projectRepository", projectRepository);
        ReflectionTestUtils.setField(projectResource, "projectService", projectService);
        this.restMvc = MockMvcBuilders.standaloneSetup(projectResource).build();
        ///When
        restMvc.perform(get("/api/projects/unknown")
            .accept(MediaType.APPLICATION_JSON))
            ///Then
            .andExpect(status().isNotFound());
    }

    @Test
    public void getAllProjects_Normal_ReceiveExpectedValues() throws Exception {
        /// Given
        List<Project> projectsList = new LinkedList<>();
        projectsList.add(testProject);
        Page<Project> page = new PageImpl<>(projectsList);
        projectRepository = Mockito.mock(ProjectRepository.class);
        when(projectRepository.findAll(any(Pageable.class))).thenReturn(page);

        /* Injection members of testing class */
        ProjectResource projectResource = new ProjectResource();
        ReflectionTestUtils.setField(projectResource, "projectRepository", projectRepository);
        ReflectionTestUtils.setField(projectResource, "projectService", projectService);
        this.restMvc = MockMvcBuilders.standaloneSetup(projectResource)
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .build();

        ///When
        restMvc.perform(get("/api/projects")
            .accept(MediaType.APPLICATION_JSON))
            ///Then
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());
    }
}