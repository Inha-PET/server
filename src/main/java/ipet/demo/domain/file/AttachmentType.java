package ipet.demo.domain.file;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AttachmentType {
    GENERAL("general"),
    IMAGE("images"),
    VIDEO("video"),
    NONE("none");

    private final String text;

    public static AttachmentType getAttachmentType(String type){
        switch (type){
            case "images":
                return IMAGE;
            case "video":
                return VIDEO;
            case "file":
                return GENERAL;
            default:
                return NONE;
        }
    }
}
