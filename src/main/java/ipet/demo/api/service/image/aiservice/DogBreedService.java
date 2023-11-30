package ipet.demo.api.service.image.aiservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DogBreedService {

    @Value("${dog-breed.path}")
    private String path;

    public BreedResultDto requestBreed(MultipartFile file) {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, Object> body = new org.springframework.util.LinkedMultiValueMap<>();
        body.add("image", file.getResource());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<?> entity = new HttpEntity<>(body, headers);

        ResponseEntity<BreedResultDto> result = restTemplate.exchange(path, HttpMethod.POST, entity, BreedResultDto.class);
        BreedResultDto breedResultDto = result.getBody();
        assert breedResultDto != null;
        breedResultDto.toKorean();

        return breedResultDto;
    }
}
