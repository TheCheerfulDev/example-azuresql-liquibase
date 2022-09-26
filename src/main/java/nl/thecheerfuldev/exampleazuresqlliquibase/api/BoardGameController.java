package nl.thecheerfuldev.exampleazuresqlliquibase.api;

import lombok.extern.slf4j.Slf4j;
import nl.thecheerfuldev.exampleazuresqlliquibase.api.dto.BoardGameDto;
import nl.thecheerfuldev.exampleazuresqlliquibase.entity.BoardGame;
import nl.thecheerfuldev.exampleazuresqlliquibase.repository.BoardGameRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class BoardGameController {

    private final BoardGameRepository boardGameRepository;

    public BoardGameController(BoardGameRepository boardGameRepository) {
        this.boardGameRepository = boardGameRepository;
    }

    @PostMapping(value = "/boardgames", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardGame> post(@RequestBody BoardGameDto boardGameDto) {
        BoardGame savedBoardGame = this.boardGameRepository.save(BoardGame.fromDto(boardGameDto));

        return ResponseEntity.created(URI.create("/boardgames/" + savedBoardGame.getId())).body(savedBoardGame);

    }

    @GetMapping("/boardgames/{id}")
    public ResponseEntity<BoardGame> get(@PathVariable("id") long id) {
        Optional<BoardGame> byId = this.boardGameRepository.findById(id);
        return ResponseEntity.of(byId);
    }

    @GetMapping("/boardgames")
    public List<BoardGame> get() {
        return this.boardGameRepository.findAll();
    }
}
