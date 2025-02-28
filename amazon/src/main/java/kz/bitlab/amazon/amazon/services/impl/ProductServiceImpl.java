package kz.bitlab.amazon.amazon.services.impl;

import kz.bitlab.amazon.amazon.models.Image;
import kz.bitlab.amazon.amazon.models.Product;
import kz.bitlab.amazon.amazon.models.User;
import kz.bitlab.amazon.amazon.repository.ProductRepository;
import kz.bitlab.amazon.amazon.repository.UserRepository;
import kz.bitlab.amazon.amazon.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public List<Product> listProducts(String title) {
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Principal principal, Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) {
        product.setUser(getUserByPrincipal(principal));
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }
        log.info("Saving new Product. Title: {}; Author email: {}", product.getTitle(), product.getUser().getEmail());
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product);
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        System.out.println(principal.getName()+">>>>>>>>>>>>>>>>>");
        if (principal == null)
            return new User();
        return userRepository.findByEmail(principal.getName());
    }

    private Image toImageEntity(MultipartFile file) {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        try {
            image.setBytes(file.getBytes());
        } catch (IOException e) {
            log.error("Error occurred while processing file: {}", file.getOriginalFilename(), e);
            image.setBytes(new byte[0]);
        }
        return image;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
