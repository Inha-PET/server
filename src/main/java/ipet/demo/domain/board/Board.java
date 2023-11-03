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

import java.time.LocalDate;
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
    @Column(columnDefinition = "varchar(20) default 'FREE'")
    private BoardType boardType;

    @Column(nullable = false, length = 30)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime incidentDateTime;

    @Column(length = 30, nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(20) default 'NORMAL'")
    private BoardStatus boardStatus;

    @Column(length = 30)
    private LocalDate deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<AwsS3File> awsS3Files = new ArrayList<>();

    @Builder
    private Board(String title, String content, BoardType boardType, LocalDateTime incidentDateTime, String location, Member member) {
        this.title = title;
        this.content = content;
        this.boardType = boardType;
        this.incidentDateTime = incidentDateTime;
        this.location = location;
        this.member = member;
        this.boardStatus = BoardStatus.NORMAL;
    }

    public static Board createNewBoardNoMember(String title, String content, BoardType boardType, LocalDateTime incidentDateTime, String location) {
        return Board.builder()
                .title(title)
                .content(content)
                .boardType(boardType)
                .incidentDateTime(incidentDateTime)
                .location(location)
                .build();
    }

    public void attachMember(Member member) {
        if (member != null) {
            this.member = member;
        }
    }

    public void updateBoard(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.boardType = board.getBoardType();
        this.incidentDateTime = board.getIncidentDateTime();
        this.location = board.getLocation();
    }

    public void deleteBoard(LocalDate deletedAt) {
        this.boardStatus = BoardStatus.DELETED;
        this.deletedAt = deletedAt;
    }

    public void restoreBoard() {
        this.boardStatus = BoardStatus.NORMAL;
        this.deletedAt = null;
    }

    public boolean isWriter(Member member) {
        return this.member.equals(member);
    }
}
