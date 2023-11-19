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
import java.util.Map;
import java.util.stream.Collectors;

@Primary
@Service
@RequiredArgsConstructor
public class LocalStorageService implements ImageStorageService {

    @Override
    public Map<String, String> store(String category, List<MultipartFile> multipartFiles, Long now) {
        return multipartFiles.stream()
                .map(multipartFile -> store(category, multipartFile, now))
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v2));
    }

    public Map<String, String> store(String category, MultipartFile multipartFile, Long now) {
        validateExists(multipartFile);

        // {project}/src/main/resources/static/{category}/{now}_{fileName}
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = LocalFileUtils.buildStoreFileName(category, originalFilename, now);
        Path filePath = LocalFileUtils.buildFilePath(storeFileName);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            assert originalFilename != null;
            return Map.of(originalFilename, storeFileName);
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
