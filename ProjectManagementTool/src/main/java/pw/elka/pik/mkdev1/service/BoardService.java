package pw.elka.pik.mkdev1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pw.elka.pik.mkdev1.domain.Board;
import pw.elka.pik.mkdev1.repository.BoardRepository;
import pw.elka.pik.mkdev1.web.rest.dto.BoardDTO;

import javax.inject.Inject;
import java.util.Optional;

@Service
@Transactional
public class BoardService {

    private final Logger log = LoggerFactory.getLogger(BoardService.class);

    @Inject
    private BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Optional<BoardDTO> getById(Long id) {
        return boardRepository.findOneById(id).map(BoardDTO::new);
    }  
    
    @Transactional(readOnly = true)
    public Optional<BoardDTO> getDetailsById(Long id) {
        return boardRepository.findOneById(id).map(b -> {
        	b.getTaskLists().forEach(t->{t.getTasks().size();
        	});
            b.getTaskLists().size();                    
            return b;
        }).map(BoardDTO::new);
    }
    
    @Transactional(readOnly = true)
    public Page<BoardDTO> getAll(Pageable pageable) {
    	return boardRepository.findAll(pageable).map(BoardDTO::new);
    }
 
    @Transactional(readOnly = true)
    public boolean exists(Long id) {
    	return getDetailsById(id).isPresent();
    }
}
