package kz.bitlab.amazon.amazon.services;

import kz.bitlab.amazon.amazon.models.Product;
import kz.bitlab.amazon.amazon.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface ProductService {

    List<Product> listProducts(String title);

    void saveProduct(Principal principal, Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3);

    User getUserByPrincipal(Principal principal);

    void deleteProduct(Long id);

    Product getProductById(Long id);
}
