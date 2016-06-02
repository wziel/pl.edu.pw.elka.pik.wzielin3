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
import pw.elka.pik.mkdev1.web.rest.dto.TaskListDTO;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BoardResource {
	
    private final Logger log = LoggerFactory.getLogger(BoardResource.class);

    @Inject
    private BoardService boardService;

    @RequestMapping(value = "/boards/{boardId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
        @Timed
        public ResponseEntity<BoardDTO> getBoard(@Valid @PathVariable Long boardId){
            log.debug("REST request to get Board : {}", boardId);
            return boardService.getDetailsById(boardId)
                .map(boardDTO -> new ResponseEntity<>(boardDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    
    @RequestMapping(value = "/boards",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<?> createBoard(@Validated(BoardDTO.CreateChecks.class) @RequestBody BoardDTO boardDTO, HttpServletRequest request) throws URISyntaxException {
        boardService.create(boardDTO);
        return new ResponseEntity<>(boardDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/boards",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<?> updateBoard(@Validated(BoardDTO.UpdateChecks.class) @RequestBody BoardDTO boardDTO) {
    	if(boardService.exists(boardDTO.getId())) {
    		boardService.update(boardDTO);
        	return new ResponseEntity<>(boardDTO, HttpStatus.OK);
    	}
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
