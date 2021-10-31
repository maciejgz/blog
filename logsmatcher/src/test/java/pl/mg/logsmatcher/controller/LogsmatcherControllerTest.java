package pl.mg.logsmatcher.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.mg.logsmatcher.model.FindPatternDto;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LogsmatcherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnCorrectResponse() throws Exception {
        Path resourceDirectory = Paths.get("src","test","resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/")
                                .content(asJsonString(new FindPatternDto(absolutePath + "\\resources.zip", "pattern")))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void emptyResultResponse() throws Exception {
        Path resourceDirectory = Paths.get("src","test","resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/")
                                .content(asJsonString(new FindPatternDto(absolutePath + "\\empty.zip", "pattern")))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void invalidFileTypeResponse() throws Exception {
        Path resourceDirectory = Paths.get("src","test","resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/")
                                .content(asJsonString(new FindPatternDto(absolutePath + "\\sample.txt", "pattern")))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void fileNotExistsResponse() throws Exception {
        Path resourceDirectory = Paths.get("src","test","resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/")
                                .content(asJsonString(new FindPatternDto(absolutePath + "\\not_exists.txt", "pattern")))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}