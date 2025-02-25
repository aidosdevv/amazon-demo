package kz.bitlab.amazon.amazon.services;


import kz.bitlab.amazon.amazon.dto.ProductDto;
import kz.bitlab.amazon.amazon.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<Product> listProducts(String title);

    void delete(Long id);

    void add(Product product, MultipartFile image1, MultipartFile image2, MultipartFile image3) throws IOException;

    Product getProductById(Long id);

    List<Product> getAll();

}
