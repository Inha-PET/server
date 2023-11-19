package ipet.demo.api.service.image.metadata;

import ipet.demo.domain.file.Attachment;
import ipet.demo.domain.file.AttachmentRepository;
import ipet.demo.exception.BusinessLogicException;
import ipet.demo.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageMetadataService {

    private final AttachmentRepository attachmentRepository;


    @Transactional
    public List<Attachment> save(String category, Map<String, String> originStore) {
        return attachmentRepository.saveAll(
                originStore.entrySet().stream()
                        .map(entry -> Attachment.createAttachment(entry.getKey(), entry.getValue(), category))
                        .toList()
        );
    }

    public Attachment getPath(Long imageId) {
        return attachmentRepository.findById(imageId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.IMAGE_NOT_FOUND));
    }
}
