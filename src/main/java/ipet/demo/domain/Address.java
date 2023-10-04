package ipet.demo.domain;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Hidden //swagger hidden annotation
@Embeddable
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Address implements Serializable {

    @Serial
    @Transient
    private static final long serialVersionUID = 2405172041950251807L;

    private String city;
    private String street;
    private String zipcode;

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
