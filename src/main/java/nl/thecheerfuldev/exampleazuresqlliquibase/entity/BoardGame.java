package nl.thecheerfuldev.exampleazuresqlliquibase.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import nl.thecheerfuldev.exampleazuresqlliquibase.api.dto.BoardGameDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardGame {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boardgame_id_generator")
    @SequenceGenerator(name = "boardgame_id_generator", sequenceName = "boardgame_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    @Min(value = 1)
    private Integer minPlayers;
    private Integer maxPlayers;

    public BoardGame(String name, Integer minPlayers, Integer maxPlayers) {
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }

    public static BoardGame fromDto(final BoardGameDto boardGameDto) {
        return new BoardGame(boardGameDto.getName(), boardGameDto.getMinPlayers(), boardGameDto.getMaxPlayers());
    }

}
