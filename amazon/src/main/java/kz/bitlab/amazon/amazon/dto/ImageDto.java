package kz.bitlab.amazon.amazon.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImageDto {

    private Long id;
    private String name;
    private String originalFilename;
    private Long size;
    private String contentType;
    private boolean isPreviewImage;
    private byte[] bytes;
    private ProductDto productDto;

}
