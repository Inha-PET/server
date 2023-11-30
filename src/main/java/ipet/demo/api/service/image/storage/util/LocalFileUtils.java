package ipet.demo.api.service.image.storage.util;

import lombok.NoArgsConstructor;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class LocalFileUtils {

    //{project}/src/main/resources/static/
    private static final Path DIRECTORY_PATH =
            Paths.get("src"
                            + File.separator + "main"
                            + File.separator + "resources"
                            + File.separator + "static"
                            + File.separator)
                    .toAbsolutePath();


    private static final String TIME_SEPARATOR = "_";
    private static final String BREED_FOLDER = "breed";

    public static String buildStoreFileName(String category, String fileName, Long now) {
        return category + File.separator + now + TIME_SEPARATOR + fileName;
    }

    public static Path buildFilePath(String storeFileName) {
        return DIRECTORY_PATH.resolve(storeFileName);
    }

    public static Path buildBreedFilePath(String storeFileName) {
        return DIRECTORY_PATH.resolve(BREED_FOLDER + File.separator + storeFileName + ".jpg");
    }

}
