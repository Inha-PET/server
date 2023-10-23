package ipet.demo.domain.file;

import ipet.demo.domain.board.Board;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class AwsS3File {

    @Id @GeneratedValue
    private Long id;
    private String originFileName;
    private String storeFileUrl;
    @Enumerated(EnumType.STRING)
    private AwsS3FileType awsS3FileType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    private AwsS3File(String originFileName, String storeFileUrl, AwsS3FileType awsS3FileType, Board board) {
        this.originFileName = originFileName;
        this.storeFileUrl = storeFileUrl;
        this.awsS3FileType = awsS3FileType;
        this.board = board;
    }

    public static AwsS3File createS3File(String originFileName, String storeFileUrl, AwsS3FileType awsS3FileType, Board board) {
        return AwsS3File.builder()
                .originFileName(originFileName)
                .storeFileUrl(storeFileUrl)
                .awsS3FileType(awsS3FileType)
                .board(board)
                .build();
    }

}
