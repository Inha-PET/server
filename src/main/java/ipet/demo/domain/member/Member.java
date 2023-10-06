package ipet.demo.domain.member;

import ipet.demo.domain.Address;
import ipet.demo.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = "id", callSuper = false) //redis test를 위해 equals와 hashcode를 id로만 구현하고, callSuper는 false
public class Member extends BaseEntity implements Serializable {

    @Serial
    @Transient
    private static final long serialVersionUID = 1905122041950251206L;

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;

    @Embedded
    private Address address;

    //todo: @ElementCollection을 사용하는데 문제 없는가 확인
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Builder
    private Member(String email, String password, String name, Address address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        roles.add("ROLE_USER");
    }

    //==생성 메서드==//
    public static Member createMember(String email, String password, String name) {
        return new Member(email, password, name, null);
    }
}

