package fr.eni.ludotheque.dal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class JeuRepositoryTest {

    @Autowired
    private JeuRepository jeuRepository;

    @Test
    void testFindAll() {
        // Placeholder for repository integration tests
    }
}
