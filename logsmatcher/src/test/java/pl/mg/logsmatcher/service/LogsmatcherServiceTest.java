package pl.mg.logsmatcher.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import pl.mg.logsmatcher.model.MatchingException;
import pl.mg.logsmatcher.model.MatchingResult;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Profile(value = "test")
class LogsmatcherServiceTest {

    @Autowired
    private LogsmatcherService logsmatcherService;

    @Test
    public void successMatchingTest() throws IOException, MatchingException, URISyntaxException {
        Path zipFilePath = Paths.get(getClass().getClassLoader().getResource("resources.zip").toURI());
        ZipFile zipFile = new ZipFile(zipFilePath.toFile());
        MatchingResult result = logsmatcherService.matchPatternInZipFile(zipFile, "pattern");
        assertEquals(3, result.getNumberOfLines());
    }

    @Test
    public void invalidFileNameTest() {
        assertThrows(NoSuchFileException.class, () -> {
            Path zipFilePath = Paths.get("/mockedPath/file.zip");
            ZipFile zipFile = new ZipFile(zipFilePath.toFile());
            MatchingResult result = logsmatcherService.matchPatternInZipFile(zipFile, "pattern");
        });
    }

    @Test
    public void emptyResultExceptionTest() throws URISyntaxException {
        URI uri = getClass().getClassLoader().getResource("empty.zip").toURI();
        assertThrows(MatchingException.class, () -> {
            Path zipFilePath = Paths.get(uri);
            ZipFile zipFile = new ZipFile(zipFilePath.toFile());
            MatchingResult result = logsmatcherService.matchPatternInZipFile(zipFile, "pattern");
            assertEquals(3, result.getNumberOfLines());
        });
    }

}