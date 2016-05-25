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

import org.hamcrest.Matchers;
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
import pw.elka.pik.mkdev1.domain.TaskList;
import pw.elka.pik.mkdev1.domain.Task;
import pw.elka.pik.mkdev1.repository.BoardRepository;
import pw.elka.pik.mkdev1.service.BoardService;
import pw.elka.pik.mkdev1.web.rest.dto.BoardDTO;
import pw.elka.pik.mkdev1.web.rest.dto.ProjectDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProjectManagementToolApp.class)
@WebAppConfiguration
@IntegrationTest
public class BoardResourceIntTest {

    @Inject
    private BoardRepository boardRepository;

    @Inject
    private BoardService boardService;

    private MockMvc restMvc;

    private Optional<BoardDTO> testOptionalBoardDTO;
    private BoardDTO testBoardDTO;
    private Board testBoard;

    @Before
    public void setUp() {
        Set<TaskList> taskLists = new HashSet<>();
        Set<Task> tasks1 = new HashSet<>();
        Set<Task> tasks2 = new HashSet<>();
        
        Task task1 = new Task();
        task1.setId(1L);
        task1.setName("Add user");
        tasks1.add(task1);
        
        Task task2 = new Task();
        task2.setId(2L);
        task2.setName("Change password");
        tasks1.add(task2);
 
        Task task3 = new Task();
        task3.setId(3L);
        task3.setName("Delete user");
        tasks2.add(task3);
        
        Task task4 = new Task();
        task4.setId(4L);
        task4.setName("Display all users");
        tasks2.add(task4);

        TaskList taskList1 = new TaskList();
        taskList1.setId(1L);
        taskList1.setName("In progress");
        taskList1.setTasks(tasks1);
        taskLists.add(taskList1);
        
        TaskList taskList2 = new TaskList();
        taskList2.setId(2L);
        taskList2.setName("Finished");
        taskList2.setTasks(tasks2);
        taskLists.add(taskList2);
        
        testBoard = new Board();
        testBoard.setId(1L);
        testBoard.setName("User Stories");
        testBoard.setTaskLists(taskLists);
        
        testBoardDTO = new BoardDTO(testBoard);
        testOptionalBoardDTO = Optional.of(testBoardDTO);
    }

    @Test
    public void getBoard_BoardExists_ReceiveExpectedValues() throws Exception {
        /// Given
    	boardService = Mockito.mock(BoardService.class);
        when(boardService.getDetailsById(1L)).thenReturn(testOptionalBoardDTO);

        /* Injection members of testing class */
        BoardResource boardResource = new BoardResource();
        ReflectionTestUtils.setField(boardResource, "boardService", boardService);
        this.restMvc = MockMvcBuilders.standaloneSetup(boardResource).build();

        /// When
        restMvc.perform(get("/api/boards/1")
            .accept(MediaType.APPLICATION_JSON))
            /// Then
            .andExpect(status().isOk())
            .andExpect(jsonPath("name").value("User Stories"));
    }
    
    @Test
    public void getBoard_BoardExists_ReceiveTaskLists() throws Exception {
        /// Given
        boardService = Mockito.mock(BoardService.class);
        when(boardService.getDetailsById(1L)).thenReturn(testOptionalBoardDTO);

        /* Injection members of testing class */
        BoardResource boardResource = new BoardResource();
        ReflectionTestUtils.setField(boardResource, "boardService", boardService);
        this.restMvc = MockMvcBuilders.standaloneSetup(boardResource).build();

        /// When
        restMvc.perform(get("/api/boards/1")
            .accept(MediaType.APPLICATION_JSON))
            /// Then
            .andExpect(status().isOk())
            .andExpect(jsonPath("taskLists").isArray())
            .andExpect(jsonPath("$.taskLists", Matchers.hasSize(2)))
            .andExpect(jsonPath("taskLists[0].id", Matchers.anyOf(Matchers.equalTo(1),Matchers.equalTo(2))))
            .andExpect(jsonPath("taskLists[1].id", Matchers.anyOf(Matchers.equalTo(1),Matchers.equalTo(2))))
            .andExpect(jsonPath("taskLists[0].name", Matchers.anyOf(Matchers.equalTo("Finished"),Matchers.equalTo("In progress"))))
            .andExpect(jsonPath("taskLists[1].name", Matchers.anyOf(Matchers.equalTo("Finished"),Matchers.equalTo("In progress"))));
        
    }
    
    @Test
    public void getBoard_BoardExists_ReceiveTasks() throws Exception {
        /// Given
        boardService = Mockito.mock(BoardService.class);
        when(boardService.getDetailsById(1L)).thenReturn(testOptionalBoardDTO);

        /* Injection members of testing class */
        BoardResource boardResource = new BoardResource();
        ReflectionTestUtils.setField(boardResource, "boardService", boardService);
        this.restMvc = MockMvcBuilders.standaloneSetup(boardResource).build();

        /// When
        restMvc.perform(get("/api/boards/1")
            .accept(MediaType.APPLICATION_JSON))
            /// Then
            .andExpect(status().isOk())
            .andExpect(jsonPath("taskLists").isArray())
            .andExpect(jsonPath("$.taskLists[0].tasks", Matchers.hasSize(2)))
            .andExpect(jsonPath("$.taskLists[1].tasks", Matchers.hasSize(2)))
            .andExpect(jsonPath("taskLists[0].tasks[0].id", Matchers.anyOf(Matchers.equalTo(1),Matchers.equalTo(2),Matchers.equalTo(3),Matchers.equalTo(4))))
            .andExpect(jsonPath("taskLists[0].tasks[1].id", Matchers.anyOf(Matchers.equalTo(1),Matchers.equalTo(2), Matchers.equalTo(3),Matchers.equalTo(4))))
            .andExpect(jsonPath("taskLists[1].tasks[0].id", Matchers.anyOf(Matchers.equalTo(1),Matchers.equalTo(2), Matchers.equalTo(3),Matchers.equalTo(4))))
            .andExpect(jsonPath("taskLists[1].tasks[1].id", Matchers.anyOf(Matchers.equalTo(1),Matchers.equalTo(2), Matchers.equalTo(3),Matchers.equalTo(4))))
            .andExpect(jsonPath("taskLists[0].tasks[0].name", Matchers.anyOf(Matchers.equalTo("Add user"),Matchers.equalTo("Delete user"),Matchers.equalTo("Change password"),Matchers.equalTo("Display all users"))))
            .andExpect(jsonPath("taskLists[0].tasks[1].name", Matchers.anyOf(Matchers.equalTo("Add user"),Matchers.equalTo("Delete user"),Matchers.equalTo("Change password"),Matchers.equalTo("Display all users"))))
            .andExpect(jsonPath("taskLists[1].tasks[0].name", Matchers.anyOf(Matchers.equalTo("Add user"),Matchers.equalTo("Delete user"),Matchers.equalTo("Change password"),Matchers.equalTo("Display all users"))))
            .andExpect(jsonPath("taskLists[1].tasks[1].name", Matchers.anyOf(Matchers.equalTo("Add user"),Matchers.equalTo("Delete user"),Matchers.equalTo("Change password"),Matchers.equalTo("Display all users"))));
    }

    @Test
    public void getBoard_BoardNotExist_ErrorStatus() throws Exception {
        ///Given
        /* Injection members of testing class */
    	BoardResource boardResource = new BoardResource();
        ReflectionTestUtils.setField(boardResource, "boardService", boardService);
        this.restMvc = MockMvcBuilders.standaloneSetup(boardResource).build();
        ///When
        restMvc.perform(get("/api/boards/123")
            .accept(MediaType.APPLICATION_JSON))
            ///Then
            .andExpect(status().isNotFound());
    }
}
