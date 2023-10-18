package ipet.demo.api.service.image.storage.local;

import ipet.demo.api.service.image.storage.ImageStorageService;
import ipet.demo.api.service.image.storage.util.LocalFileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Primary
@Service
@RequiredArgsConstructor
public class LocalStorageService implements ImageStorageService {

    @Override
    public List<String> store(String category, List<MultipartFile> multipartFiles, Long now) {
        return multipartFiles.stream()
                .map(multipartFile -> store(category, multipartFile, now))
                .toList();
    }

    public String store(String category, MultipartFile multipartFile, Long now) {
        validateExists(multipartFile);

        // {project}/src/main/resources/static/{category}/{now}_{fileName}
        Path filePath = LocalFileUtils.buildFilePath(category, multipartFile.getOriginalFilename(), now);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch (IOException e) {
            //custom으로 변경
            throw new RuntimeException("파일 저장에 실패했습니다.", e);
        }
    }

    private static void validateExists(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            //custom으로 변경
            throw new RuntimeException("빈 파일을 저장할 수 없습니다.");
        }
    }
}
