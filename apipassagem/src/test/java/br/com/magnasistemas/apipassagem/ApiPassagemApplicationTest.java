package br.com.magnasistemas.apipassagem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ApiPassagemApplicationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
   void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void mainMethodShouldStartApplication() {
    	ApiPassagemApplication.main(new String[]{});
        assertTrue(outContent.toString().contains("Started ApiPassagemApplication"));
    }
}
