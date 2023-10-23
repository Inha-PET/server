package ipet.demo.api.service.image.storage;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageStorageService {

    List<String> store(String category, List<MultipartFile> multipartFiles, Long now);

}
