package pl.mg.logsmatcher.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.mg.logsmatcher.files.service.FileService;
import pl.mg.logsmatcher.model.FindPatternDto;
import pl.mg.logsmatcher.model.InvalidFileFormatException;
import pl.mg.logsmatcher.model.MatchingException;
import pl.mg.logsmatcher.model.MatchingResult;
import pl.mg.logsmatcher.service.LogsmatcherService;

import java.io.IOException;
import java.util.zip.ZipFile;

@RestController
@RequestMapping(value = "")
@Slf4j
public class LogsmatcherController {

    private final LogsmatcherService logsmatcherService;
    private final FileService fileService;

    public LogsmatcherController(LogsmatcherService logsmatcherService, FileService fileService) {
        this.logsmatcherService = logsmatcherService;
        this.fileService = fileService;
    }

    @PostMapping(value = "")
    public ResponseEntity<MatchingResult> countLogs(@RequestBody FindPatternDto body) {
        try {
            ZipFile path = fileService.findFileLocally(body.getPath());
            return ResponseEntity.ok(logsmatcherService.matchPatternInZipFile(path, body.getPattern()));
        } catch (IOException e) {
            log.debug(e.getLocalizedMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Input file not found", e);
        } catch (InvalidFileFormatException e) {
            log.debug(e.getLocalizedMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file format. ZIP required", e);
        } catch (MatchingException e) {
            log.debug(e.getLocalizedMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matching pattern exception", e);
        }

    }

}
