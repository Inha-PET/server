package ipet.demo.api.service.image.facade;

import ipet.demo.api.service.image.aiservice.BreedResultDto;
import ipet.demo.api.service.image.aiservice.BreedType;
import ipet.demo.api.service.image.aiservice.DogBreedService;
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
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

    private final ImageStorageService imageStorageService;
    private final ImageMetadataService imageMetadataService;
    private final DogBreedService dogBreedService;

    public String test() {
        return null;
    }

    public List<ImageResponse> upload(String category, List<MultipartFile> multipartFiles, long now) {
        //파일 저장
        //원본 파일 이름: image.png -> 저장 파일 이름: images/{now}_image.png
        Map<String, String> originStore = imageStorageService.store(category, multipartFiles, now);

        //DB에 저장
        List<Attachment> savedAttachments = imageMetadataService.save(category, originStore);

        List<ImageResponse> imageResponses = savedAttachments.stream()
                .map(ImageResponse::fromEntity)
                .toList();

        //AI 분석
        // 각 multipartFiles가 dogBreedService.requestBreed()의 인자로 들어가야 한다.
        // 그 결과값은 imageResponses에 setAi()로 저장되어야 한다.

        for (int i = 0; i < multipartFiles.size(); i++) {
            imageResponses.get(i).setAi(dogBreedService.requestBreed(multipartFiles.get(i)));
        }

        log.info("==========================AI 분석 시작======================");
        log.info("{}", dogBreedService.requestBreed(multipartFiles.get(0)));
        log.info("==========================AI 분석 끝========================");

        return imageResponses;
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

    public byte[] getImage(String breedkoreanName) {
        Path filePath = LocalFileUtils.buildBreedFilePath(Objects.requireNonNull(BreedType.getBreedTypeByKoreanName(breedkoreanName)).getEnglishName());

        try (InputStream fileInputStream = new FileInputStream(filePath.toFile())){
            return fileInputStream.readAllBytes();
        } catch (IOException e) {
            throw new BusinessLogicException(ExceptionCode.FILE_IO_EXCEPTION, e);
        }
    }
}
