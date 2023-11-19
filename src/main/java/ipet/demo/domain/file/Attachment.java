package ipet.demo.domain.file;

import ipet.demo.domain.BaseEntity;
import ipet.demo.domain.board.Board;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Attachment extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String originFileName;

    private String storeFileName;

    private AttachmentType type;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    private Attachment(String originFileName, String storeFileName, AttachmentType type) {
        this.originFileName = originFileName;
        this.storeFileName = storeFileName;
        this.type = type;
    }

    public static Attachment createAttachment(String originFileName, String storeFileName, String type) {
        AttachmentType attachmentType = AttachmentType.getAttachmentType(type);

        return Attachment.builder()
                .originFileName(originFileName)
                .storeFileName(storeFileName)
                .type(attachmentType)
                .build();
    }

    public void attachBoard(Board board) {
        this.board = board;
    }

    public void detachBoard() {
        this.board = null;
    }
}
