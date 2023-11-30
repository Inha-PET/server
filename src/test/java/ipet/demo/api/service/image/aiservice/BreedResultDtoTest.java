package ipet.demo.api.service.image.aiservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BreedResultDtoTest {

    @Test
    void toKorean() {
        BreedResultDto breedResultDto = new BreedResultDto();
        breedResultDto.setFirst("dhole");
        breedResultDto.setSecond("dingo");
        breedResultDto.setThird("african_hunting_dog");
        breedResultDto.toKorean();
        assertEquals("돌", breedResultDto.getFirst());
        assertEquals("들개", breedResultDto.getSecond());
        assertEquals("아프리카 사냥개", breedResultDto.getThird());
    }

}