package ipet.demo.api.service.image.storage;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ImageStorageService {

    Map<String, String> store(String category, List<MultipartFile> multipartFiles, Long now);

}
