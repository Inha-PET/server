package ipet.demo.api.service.image.facade;

import ipet.demo.api.service.image.metadata.ImageMetadataService;
import ipet.demo.api.service.image.response.ImageResponse;
import ipet.demo.api.service.image.storage.ImageStorageService;
import ipet.demo.api.service.image.storage.util.LocalFileUtils;
import ipet.demo.domain.file.Attachment;
import ipet.demo.exception.BusinessLogicException;
import ipet.demo.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

    private final ImageStorageService imageStorageService;
    private final ImageMetadataService imageMetadataService;

    public String test() {
        return null;
    }

    public List<ImageResponse> upload(String category, List<MultipartFile> multipartFiles, long now) {
        //파일 저장
        //원본 파일 이름: image.png -> 저장 파일 이름: images/{now}_image.png
        Map<String, String> originStore = imageStorageService.store(category, multipartFiles, now);

        //DB에 저장
        List<Attachment> savedAttachments = imageMetadataService.save(category, originStore);

        return savedAttachments.stream()
                .map(ImageResponse::fromEntity)
                .toList();
    }

    public byte[] getImage(Long imageId) {
        Attachment attachment = imageMetadataService.getPath(imageId);
        Path filePath = LocalFileUtils.buildFilePath(attachment.getStoreFileName());

        try (InputStream fileInputStream = new FileInputStream(filePath.toFile())){
            return fileInputStream.readAllBytes();
        } catch (IOException e) {
            throw new BusinessLogicException(ExceptionCode.FILE_IO_EXCEPTION, e);
        }

    }
}
