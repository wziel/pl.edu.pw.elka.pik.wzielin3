package pw.elka.pik.mkdev1.web.rest;

import com.codahale.metrics.annotation.Timed;
import pw.elka.pik.mkdev1.domain.Board;
import pw.elka.pik.mkdev1.domain.User;
import pw.elka.pik.mkdev1.repository.BoardRepository;
import pw.elka.pik.mkdev1.repository.UserRepository;
import pw.elka.pik.mkdev1.service.MailService;
import pw.elka.pik.mkdev1.service.BoardService;
import pw.elka.pik.mkdev1.web.rest.dto.ManagedUserDTO;
import pw.elka.pik.mkdev1.web.rest.dto.ProjectDTO;
import pw.elka.pik.mkdev1.web.rest.dto.BoardDTO;
import pw.elka.pik.mkdev1.web.rest.util.HeaderUtil;
import pw.elka.pik.mkdev1.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects/{name}")
public class BoardResource {

    private final Logger log = LoggerFactory.getLogger(BoardResource.class);

    @Inject
    private BoardRepository boardRepository;

    @Inject
    private BoardService boardService;

//    @RequestMapping(value = "/boards/{name}",
//        method = RequestMethod.GET,
//        produces = MediaType.APPLICATION_JSON_VALUE)
//    @Timed
//    public ResponseEntity<BoardDTO> getBoard(@PathVariable String name){
//        log.debug("REST request to get Board : {}", name);
//        return boardService.getBoardWithTasksByName(name)
//            .map(BoardDTO::new)
//            .map(boardDTO -> new ResponseEntity<>(boardDTO, HttpStatus.OK))
//            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @RequestMapping(value = "/projects/{name}/boards",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
        @Timed
        @Transactional(readOnly = true)
        public ResponseEntity<List<BoardDTO>> getAllBoards(Pageable pageable)
            throws URISyntaxException {
            Page<BoardDTO> page = boardService.getAll(pageable);
            List<BoardDTO> boardDTOs = page.getContent().stream()
                .collect(Collectors.toList());
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/projects/{name}/boards");
            return new ResponseEntity<>(boardDTOs, headers, HttpStatus.OK);
        }

    @RequestMapping(value = "/projects/{name}/boards/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
        @Timed
        public ResponseEntity<BoardDTO> getBoard(@PathVariable Long id){
            log.debug("REST request to get Board : {}", id);
            return boardService.getDetailsById(id)
                .map(boardDTO -> new ResponseEntity<>(boardDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
}
