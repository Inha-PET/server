package ipet.demo.api.service.image.facade;

import ipet.demo.api.service.image.metadata.ImageMetadataService;
import ipet.demo.api.service.image.storage.ImageStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

    private final ImageStorageService imageStorageService;
    private final ImageMetadataService imageMetadataService;

    public String test() {
        return null;
    }

    public String upload(String category, List<MultipartFile> multipartFiles, long now) {
        List<String> resourcePaths = imageStorageService.store(category, multipartFiles, now);
        return resourcePaths.get(0);
    }
}
