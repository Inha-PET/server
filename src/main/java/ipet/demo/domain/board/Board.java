package ipet.demo.domain.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import ipet.demo.domain.BaseEntity;
import ipet.demo.domain.file.AwsS3File;
import ipet.demo.domain.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class Board extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(length = 1005, nullable = false)
    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime incidentDateTime;

    @Column(length = 30, nullable = false)
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<AwsS3File> awsS3Files = new ArrayList<>();

    @Builder
    private Board(String title, String content, BoardType boardType, LocalDateTime incidentDateTime, String location, Member member){
        this.title = title;
        this.content = content;
        this.boardType = boardType;
        this.incidentDateTime = incidentDateTime;
        this.location = location;
        this.member = member;
    }

    public static Board createNewBoard(String title, String content, BoardType boardType, LocalDateTime incidentDateTime, String location, Member member) {
        return Board.builder()
                .title(title)
                .content(content)
                .boardType(boardType)
                .incidentDateTime(incidentDateTime)
                .location(location)
                .member(member)
                .build();
    }

}
