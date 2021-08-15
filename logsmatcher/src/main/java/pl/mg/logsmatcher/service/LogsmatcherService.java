package pl.mg.logsmatcher.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.mg.logsmatcher.model.MatchingException;
import pl.mg.logsmatcher.model.MatchingResult;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
@Slf4j
public class LogsmatcherService {

    public static final String PATTERN_WRAPPER = ".*%s.*";

    public MatchingResult matchPatternInZipFile(@NotNull ZipFile file, @NotBlank String pattern) throws MatchingException, IOException {
        ArrayList<String> lines = new ArrayList<>();

        Enumeration<? extends ZipEntry> entries = file.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            try (InputStream inputStream = file.getInputStream(zipEntry); Scanner scanner = new Scanner(inputStream)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (matchLine(line, pattern)) {
                        lines.add(line);
                    }
                }
            }
        }
        if (CollectionUtils.isEmpty(lines)) {
            //just for tests
            throw new MatchingException("Empty result");
        }
        return new MatchingResult(lines.size(), lines);
    }


    private boolean matchLine(String line, String pattern) {
        Pattern patternValue = Pattern.compile(String.format(PATTERN_WRAPPER, pattern));
        return patternValue.matcher(line).find();
    }
}
