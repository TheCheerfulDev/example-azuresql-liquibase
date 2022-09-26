package nl.thecheerfuldev.exampleazuresqlliquibase.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardGameDto {

    private final String name;
    private final int minPlayers;
    private final int maxPlayers;

}
