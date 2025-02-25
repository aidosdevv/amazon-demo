package kz.bitlab.amazon.amazon.services.impl;

import kz.bitlab.amazon.amazon.models.Image;
import kz.bitlab.amazon.amazon.models.Product;
import kz.bitlab.amazon.amazon.repository.ProductRepository;
import kz.bitlab.amazon.amazon.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    public List<Product> listProducts(String title) {
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    public void add(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1 = new Image();
        Image image2 = new Image();
        Image image3 = new Image();
        convert(product,file1,image1);
        convert(product,file2,image2);
        convert(product,file3,image3);
        log.info("Saving new Product. Title: {}; Author: {}", product.getTitle(), product.getAuthor());
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product);
    }

    public void convert(Product product, MultipartFile file,Image image) throws IOException {
        if (file.getSize() != 0) {
            image = toImageEntity(file);
            image.setPreviewImage(true);
            product.addImageToProduct(image);
        }
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFilename(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }





}
