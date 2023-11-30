package ipet.demo.api.service.image.aiservice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@ToString
@Setter
public class BreedResultDto {

    private String first;
    private String second;
    private String third;

    public void toKorean() {
        this.first = Objects.requireNonNull(BreedType.getBreedTypeByEnglishName(first)).getKoreanName();
        this.second = Objects.requireNonNull(BreedType.getBreedTypeByEnglishName(second)).getKoreanName();
        this.third = Objects.requireNonNull(BreedType.getBreedTypeByEnglishName(third)).getKoreanName();
    }

}
