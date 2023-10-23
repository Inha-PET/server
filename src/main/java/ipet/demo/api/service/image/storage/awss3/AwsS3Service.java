package ipet.demo.api.service.image.storage.awss3;

import ipet.demo.api.service.image.storage.ImageStorageService;
import ipet.demo.api.service.image.storage.util.AwsS3FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AwsS3Service implements ImageStorageService {

//    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public String test() {
        log.info("S3ImageService");
        return "S3ImageService";
    }

    @Override
    public List<String> store(String category, List<MultipartFile> multipartFiles, Long now) {
//        List<MultipartFile> validMultipartFiles = multipartFiles.stream()
//                .filter(this::validateFileExists)
//                .filter(this::isOriginNameNullOrEmpty)
//                .toList();
//
//        validateFileSize(validMultipartFiles);

        List<String> fileUrls = new ArrayList<>();

//        for (MultipartFile validMultipartFile : validMultipartFiles) {
//            // MAC은 NFD, Windows는 NFC를 따르는데 한글파일명에 문제가 있어 NFC로 통일
//            String normalizedFileName = Normalizer.normalize(validMultipartFile.getOriginalFilename(), Normalizer.Form.NFC);
//            // AWS S3에서 파일명 바꿔주는 옵션 있음
//            String fileName = AwsS3FileUtils.buildFileName(category, normalizedFileName, now);
//
//            ObjectMetadata objectMetadata = new ObjectMetadata();
//            objectMetadata.setContentLength(validMultipartFile.getSize());
//            objectMetadata.setContentType(validMultipartFile.getContentType());

//            try (InputStream inputStream = validMultipartFile.getInputStream()) {
//                amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
//                        .withCannedAcl(CannedAccessControlList.PublicRead));
//                fileUrls.add(amazonS3Client.getUrl(bucketName, fileName).toString());
//            } catch (IOException e) {
//                //TODO custom exception
//                throw new RuntimeException(e);
//            }
//        }

        return fileUrls;
    }

//    private boolean isOriginNameNullOrEmpty(MultipartFile multipartFile) {
//        if (StringUtils.isNullOrEmpty(multipartFile.getOriginalFilename())) {
//            throw new RuntimeException("파일 이름이 없습니다.");
//        }
//        return true;
//    }

//    private boolean validateFileExists(MultipartFile multipartFile) {
//        if (multipartFile.isEmpty()) {
//            //TODO custom exception 만들기
//            throw new RuntimeException("파일 없음");
//        }
//        return true;
//    }
//
//    private void validateFileSize(List<MultipartFile> validMultipartFiles) {
//        if (validMultipartFiles.size() > 10) {
//            throw new RuntimeException("파일은 10개까지만 업로드할 수 있습니다.");
//        }
//    }
}
