package kz.bitlab.amazon.amazon.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name ="description" , columnDefinition = "text")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "city")
    private String city;

    @Column(name = "author")
    private String author;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    private List<Image> images = new ArrayList<>();

    private Long previewImageId;

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }

    public void addImageToProduct(Image image){
        if (image == null) {
            throw new IllegalArgumentException("Image cannot be null");
        }

        image.setProduct(this);
        images.add(image);

        // Если у продукта еще нет превью, устанавливаем первую загруженную картинку
        if (previewImageId == null) {
            previewImageId = image.getId();
        }
    }

}
