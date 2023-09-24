package ipet.demo.domain.member;

import ipet.demo.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity implements Serializable {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;

    //필요 없을 지도
    private String phone;

    //todo: Address 클래스 만들어서 사용하기
    private String address;

    //todo: @ElementCollection을 사용하는데 문제 없는가 확인
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Builder
    private Member(String email, String password, String name, String phone, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        roles.add("ROLE_USER");
    }

    //==생성 메서드==//
    public static Member createMember(String email, String password, String name, String phone, String address) {
        return new Member(email, password, name, phone, address);
    }
}

