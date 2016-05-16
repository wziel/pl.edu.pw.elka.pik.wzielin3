package pw.elka.pik.mkdev1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pw.elka.pik.mkdev1.domain.Board;
import pw.elka.pik.mkdev1.repository.BoardRepository;
import pw.elka.pik.mkdev1.web.rest.dto.ProjectDTO;

import javax.inject.Inject;
import java.util.Optional;

@Service
@Transactional
public class BoardService {

    private final Logger log = LoggerFactory.getLogger(BoardService.class);

    @Inject
    private BoardRepository boardRepository;

//    @Transactional(readOnly = true)
//    public Optional<Board> getBoardWithTasksByName(String name) {
//        return boardRepository.findOneByName(name).map(b -> {
//            b.getTasks().size();
//            return b;
//        });
//    }
}
