package ipet.demo.domain.file;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AwsS3FileType {
    GENERAL("general"),
    IMAGE("image"),
    VIDEO("video");

    private final String text;

}
