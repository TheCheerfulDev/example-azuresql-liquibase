package nl.thecheerfuldev.exampleazuresqlliquibase;

import nl.thecheerfuldev.exampleazuresqlliquibase.api.BoardGameController;
import nl.thecheerfuldev.exampleazuresqlliquibase.api.dto.BoardGameDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ExampleAzuresqlLiquibaseApplicationTests {

    @Container
    private static final GenericContainer<?> DATABASE = new GenericContainer<>("mcr.microsoft.com/azure-sql-edge:1.0.6")
            .withEnv("ACCEPT_EULA", "1")
            .withEnv("MSSQL_SA_PASSWORD", "yourStrong(!)Password")
            .withExposedPorts(1433);

    @Autowired
    private BoardGameController boardGameController;

    @Autowired
    private MockMvc mockMvc;

    @DynamicPropertySource
    private static void init(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> "jdbc:sqlserver://127.0.0.1:" + DATABASE.getMappedPort(1433) + ";encrypt=true;trustServerCertificate=true;");
    }

    @Test
    void contextLoads() {
        boardGameController.post(new BoardGameDto("Bla", 1, 1));

        assertThat(boardGameController.get()).hasSize(1);
    }

    @Test
    void withMvc() throws Exception {
        this.mockMvc.perform(get("/boardgames")).andExpect(status().isOk());
    }
}

