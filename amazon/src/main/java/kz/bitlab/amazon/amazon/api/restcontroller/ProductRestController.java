package kz.bitlab.amazon.amazon.api.restcontroller;

import kz.bitlab.amazon.amazon.models.Product;
import kz.bitlab.amazon.amazon.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;


    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAll();
    }
}
