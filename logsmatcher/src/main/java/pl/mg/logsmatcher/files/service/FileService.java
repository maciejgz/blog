package pl.mg.logsmatcher.files.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import pl.mg.logsmatcher.model.InvalidFileFormatException;

import javax.validation.constraints.NotEmpty;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipFile;

@Controller
@Slf4j
public class FileService {

    private static final List<String> ALLOWED_EXTENSIONS = List.of("zip");
    private static final List<String> ALLOWED_MIME_TYPES = List.of("application/x-zip-compressed", "application/zip-compressed");

    public ZipFile findFileLocally(@NotEmpty String path) throws IOException, InvalidFileFormatException {
        Path filePath = Path.of(path);
        File zipFile = filePath.toFile();
        if (!zipFile.exists()) {
            throw new IOException("File " + path + " not found");
        }

        String extension = FilenameUtils.getExtension(zipFile.getName());
        if (!ALLOWED_EXTENSIONS.contains(extension.toLowerCase(Locale.ROOT))) {
            throw new InvalidFileFormatException("Invalid file format exception of file: " + zipFile.getName());
        }

        String probedContent = Files.probeContentType(filePath);
        if (!ALLOWED_MIME_TYPES.contains(probedContent.toLowerCase(Locale.ROOT))) {
            throw new InvalidFileFormatException("File format " + probedContent + "is invalid");
        }

        return new ZipFile(zipFile);
    }
}
