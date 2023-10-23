package ipet.demo.api.service.image.storage.util;

import lombok.NoArgsConstructor;
import org.springframework.http.ContentDisposition;

import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class AwsS3FileUtils {
    private static final String FILE_EXTENSION_SEPARATOR = ".";
    private static final String CATEGORY_PREFIX = "/";
    private static final String TIME_SEPARATOR = "_";
    private static final int UNDER_BAR_INDEX = 1;

    public static String buildFileName(String category, String originalFileName, Long now) {
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR); //test.jpeg
        String fileExtension = originalFileName.substring(fileExtensionIndex);
        String fileName = originalFileName.substring(0, fileExtensionIndex);
        String nowStr = String.valueOf(now);

        return category + CATEGORY_PREFIX + fileName + TIME_SEPARATOR + nowStr + fileExtension;
    }

    public static ContentDisposition createContentDisposition(String categoryWithFileName) {
        String fileName = categoryWithFileName.substring(
                categoryWithFileName.lastIndexOf(CATEGORY_PREFIX) + UNDER_BAR_INDEX);
        return ContentDisposition.builder("attachment")
                .filename(fileName, StandardCharsets.UTF_8)
                .build();
    }
}
