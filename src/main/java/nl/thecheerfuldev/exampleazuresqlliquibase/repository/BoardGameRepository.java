package nl.thecheerfuldev.exampleazuresqlliquibase.repository;

import nl.thecheerfuldev.exampleazuresqlliquibase.entity.BoardGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {
}
