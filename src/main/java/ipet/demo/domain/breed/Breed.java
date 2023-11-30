package ipet.demo.domain.breed;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Breed {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String imageFileUrl;


}
