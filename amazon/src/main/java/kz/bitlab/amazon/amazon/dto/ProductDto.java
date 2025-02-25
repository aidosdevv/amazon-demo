package kz.bitlab.amazon.amazon.dto;

import jakarta.persistence.PrePersist;
import kz.bitlab.amazon.amazon.models.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class ProductDto {

    private Long id;
    private String title;
    private String description;
    private double price;
    private String city;
    private String author;

    private List<Image> images = new ArrayList<>();

    private Long previewImageId;

    private LocalDateTime dateOfCreated;


}
